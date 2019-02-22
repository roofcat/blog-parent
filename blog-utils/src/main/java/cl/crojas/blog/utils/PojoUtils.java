package cl.crojas.blog.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.collection.internal.PersistentBag;

/**
 * Clase que permite copiar todos los atributos de de una clase hacia otra clase
 * (Siendo ambas clases de diferente tipo) Se encuentra pensada para automatizar
 * el proceso de mapeo de atributos entre POJOS.
 * 
 * @author Christian Rojas N.
 *
 */
public class PojoUtils {

	private static final Logger logger = Logger.getLogger(PojoUtils.class);

	private static final String ENTITY = "Entity";

	private static final String DTO = "DTO";

	private static final String CUSTOM_MAPPING = "customMapping";

	private static final String ERROR_LISTA = "Lista No Pudo Ser Generada por Problemas de Casteo.";

	private PojoUtils() {
		throw new IllegalStateException("Clase Utilitaria");
	}

	/**
	 * Método que permite copiar los atributos del objeto from hasta el objeto to
	 * 
	 * @param from objeto de origen de donde se copian los atributos
	 * @param to   objeto de destino en donde son copiados los atributos del objeto
	 *             from.
	 * @author Christian Rojas N.
	 * @throws Exception
	 * 
	 */
	public static <F, T> void copyAllFields(F from, T to) {
		copyAllFields(from, to, null, null);
	}

	/**
	 * Método que permite copiar los atributos del objeto from hasta el objeto to
	 * Adicionalmente permite mapear campos dentro de objetos con nombres
	 * diferentes.
	 * 
	 * @param from objeto de origen de donde se copian los atributos
	 * @param to   objeto de destino en donde son copiados los atributos del objeto
	 *             from.
	 * @param      Map<String, String> mapping, key con el nombre del atributo del
	 *             objeto de origen y value con el nombre del atributo del objeto de
	 *             destino.
	 * @author Christian Rojas N.
	 * @throws Exception
	 * 
	 */
	public static <F, T> void copyAllFields(F from, T to, Map<String, String> mapping) {
		copyAllFields(from, to, null, mapping);
	}

	/**
	 * Método que permite copiar los atributos del objeto from hasta el objeto to
	 * Adicionalmente permite agregar una lista con campos a excluir en el proceso
	 * de mapeo.
	 * 
	 * @param from objeto de origen de donde se copian los atributos
	 * @param to   objeto de destino en donde son copiados los atributos del objeto
	 *             from.
	 * @param      String[] exclusion Arreglo con el nombre de los campos a excluir.
	 * @author Christian Rojas N.
	 * @throws Exception
	 * 
	 */
	public static <F, T> void copyAllFields(F from, T to, String[] exclusion) {
		copyAllFields(from, to, exclusion, null);
	}

	/**
	 * Método que permite copiar los atributos del objeto from hasta el objeto to
	 * Adicionalmente permite agregar una lista con campos a excluir en el proceso
	 * de mapeo. Adicionalmente permite mapear campos dentro de objetos con nombres
	 * diferentes.
	 * 
	 * @param from objeto de origen de donde se copian los atributos
	 * @param to   objeto de destino en donde son copiados los atributos del objeto
	 *             from.
	 * @param      String[] exclusion Arreglo con el nombre de los campos a excluir.
	 * @param      Map<String, String> mapping, key con el nombre del atributo del
	 *             objeto de origen y value con el nombre del atributo del objeto de
	 *             destino.
	 * @author Christian Rojas N.
	 * @throws Exception
	 * 
	 */
	public static <F, T> void copyAllFields(F from, T to, String[] exclusion, Map<String, String> mapping) {
		copyAllFields(from, to, exclusion, mapping, null);
	}

	/**
	 * Método para Validar los Objetos de Entrada
	 * 
	 * @param from objeto de origen de donde se copian los atributos
	 * @param to   objeto de destino en donde son copiados los atributos del objeto
	 *             from.
	 * @param objs Objeto Map<Object, Object> con los Objetos a Ser Casteados
	 * @author G. González
	 * 
	 */
	private static <F, T> Map<Object, Object> validarDatosEntrada(F from, T to, Map<Object, Object> objs) {

		// VALIDACION DE OBJETOS DE ENTRADA
		if (objs == null) {
			objs = new HashMap<>();
		}

		objs.put(from, to);

		if (from == null) {

			logger.error("Debe especificar objeto from.");

			throw new NullPointerException("Debe especificar objeto from.");

		}

		if (to == null) {

			logger.error("Debe especificar objeto to.");

			throw new NullPointerException("Debe especificar objeto to.");

		}

		return objs;

	}

	/**
	 * Método que permite copiar los atributos del objeto from hasta el objeto to
	 * Adicionalmente permite agregar una lista con campos a excluir en el proceso
	 * de mapeo. Adicionalmente permite mapear campos dentro de objetos con nombres
	 * diferentes. La Anotación @SuppressWarnings indica que se desactivaron los
	 * warnings de este método, en este caso, por clase no chequeada (establecida)
	 * al instanciar clases origen y destino
	 * 
	 * @param from objeto de origen de donde se copian los atributos
	 * @param to   objeto de destino en donde son copiados los atributos del objeto
	 *             from.
	 * @param      String[] exclusion Arreglo con el nombre de los campos a excluir.
	 * @param      Map<String, String> mapping, key con el nombre del atributo del
	 *             objeto de origen y value con el nombre del atributo del objeto de
	 *             destino.
	 * @param objs Objeto Map<Object, Object> con los Objetos a Ser Casteados
	 * @author Christian Rojas N.
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <F, T> void copyAllFields(F from, T to, String[] exclusion, Map<String, String> mapping,
			Map<Object, Object> objs) {

		// VALIDACION DE OBJETOS DE ENTRADA
		Map<Object, Object> objetos = validarDatosEntrada(from, to, objs);

		// OBTENCIÓN CLASE DE ORIGEN Y DESTINO
		Class<F> clazzfFrom = (Class<F>) from.getClass();

		Class<T> clazztO = (Class<T>) to.getClass();

		// OBTENCIÓN DE ATRIBUTOS DESDE LA CLASE DE ORIGEN
		List<Field> allFieldsFrom = getAllModelFields(clazzfFrom);

		if (allFieldsFrom != null) {

			for (Field fieldFrom : allFieldsFrom) {

				try {

					// EXCLUIR LOS ATRIBUTOS ESPECIFICADOS
					boolean exclude = excluir(exclusion, fieldFrom);

					// BUSCAMOS LA NUEVA DEFINICIÓN DE MAPEO - CON EL CAMPO DE ORIGEN EL CAMPO DE
					// DESTINO
					Map<String, Object> customMapping = mapear(mapping, fieldFrom);

					if (!exclude) {

						// OBTENCION DEL VALOR DEL CAMPO DE ORIGEN
						fieldFrom.setAccessible(true);

						Object objFieldFrom = fieldFrom.get(from);

						if (objFieldFrom == null || objFieldFrom instanceof PersistentBag) {
							continue;
						}

						// OBTENCION DEL CAMPO DE DESTINO
						Field fieldto = null;

						if ((boolean) customMapping.get(CUSTOM_MAPPING)) {

							fieldto = obtenerCampoDestinoWithMapping(clazztO,
									(String) customMapping.get("fieldToMapped"));

						} else {

							fieldto = obtenerCampoDestinoWithoutMapping(clazztO, fieldFrom);

						}

						// REGLAS DE CONVERSIÓN PERSONALIZADAS PARA FACILITAR EL PROCESO DE COPIA AL
						// USUARIO
						// EJ: A PESAR QUE EL OBJETO DATE SE ENCUENTRE DEFINIDO DE FORMA DIFERENTE EN
						// LAS CLASES ES TRANSFORMADO AL TIPO CORRESPONDIENTE Y COPIADO.
						if (fieldFrom.getType() == java.util.Date.class && fieldto.getType() == java.sql.Date.class) {

							java.util.Date date = (java.util.Date) objFieldFrom;

							objFieldFrom = new java.sql.Date(date.getTime());

						} else {

							if (fieldFrom.getType() == java.sql.Date.class
									&& fieldto.getType() == java.util.Date.class) {

								java.sql.Date date = (java.sql.Date) objFieldFrom;

								objFieldFrom = new java.util.Date(date.getTime());

							} else {

								if ((fieldFrom.getType().equals(Integer.class) || fieldFrom.getType().equals(int.class))
										&& (fieldto.getType().equals(Integer.class)
												|| fieldto.getType().equals(int.class))) {

									// No Hace Nada

								} else {

									if ((fieldFrom.getType().equals(Float.class)
											|| fieldFrom.getType().equals(float.class))
											&& (fieldto.getType().equals(Float.class)
													|| fieldto.getType().equals(float.class))) {

										// No Hace Nada

									} else {

										if ((fieldFrom.getType().equals(Double.class)
												|| fieldFrom.getType().equals(double.class))
												&& (fieldto.getType().equals(Double.class)
														|| fieldto.getType().equals(double.class))) {

											// No Hace Nada

										} else {

											if ((fieldFrom.getType().equals(Character.class)
													|| fieldFrom.getType().equals(char.class))
													&& (fieldto.getType().equals(Character.class)
															|| fieldto.getType().equals(char.class))) {

												// No Hace Nada

											} else {

												if ((fieldFrom.getType().equals(Long.class)
														|| fieldFrom.getType().equals(long.class))
														&& (fieldto.getType().equals(Long.class)
																|| fieldto.getType().equals(long.class))) {

													// No Hace Nada

												} else {

													if ((fieldFrom.getType().equals(Short.class)
															|| fieldFrom.getType().equals(short.class))
															&& (fieldto.getType().equals(Short.class)
																	|| fieldto.getType().equals(short.class))) {

														// No Hace Nada

													} else {

														if ((fieldFrom.getType().equals(Boolean.class)
																|| fieldFrom.getType().equals(boolean.class))
																&& (fieldto.getType().equals(Boolean.class)
																		|| fieldto.getType().equals(boolean.class))) {

															// No Hace Nada

														} else {

															if ((fieldFrom.getType().equals(Byte.class)
																	|| fieldFrom.getType().equals(byte.class))
																	&& (fieldto.getType().equals(Byte.class)
																			|| fieldto.getType().equals(byte.class))) {

																// No Hace Nada

															} else if ((fieldFrom.getType().equals(java.util.List.class)
																	&& (fieldto.getType().equals(java.util.List.class))
																	&& objFieldFrom != null)) {

																objFieldFrom = validarListasEntityDTO(fieldFrom,
																		fieldto, objetos, objFieldFrom);

															} else {

																if (fieldFrom.getType() != fieldto.getType()) {

																	objFieldFrom = validarEntityDTO(fieldFrom, fieldto,
																			objetos, objFieldFrom);

																}

															}

														}

													}

												}

											}

										}

									}

								}

							}

						}

						// ACTUALIZACIÓN DEL CAMPO (FIELDTO) EN EL OBJETO (OBJECTTO) CON EL VALOR
						// (OBJECTFIELDFROM)
						fieldto.setAccessible(true);

						fieldto.set(to, objFieldFrom);

					}

				} catch (IllegalAccessException | NoSuchFieldException ex) {

					// No Hace Nada

				}

			}

		} else {

			logger.error("Objeto de origen no posee atributos.");

			throw new ClassCastException("Objeto de origen no posee atributos.");

		}

	}

	/**
	 * Método para evaluar si un campo es objeto de exclusión o no
	 * 
	 * @param           String[] exclusion Arreglo con el nombre de los campos a
	 *                  excluir.
	 * @param fieldFrom Objeto Field correspondiente al Campo a ser Evaluado para
	 *                  Excluir
	 * @return boolean True si excluye, False si no
	 * @author G. González
	 * 
	 */
	private static boolean excluir(String[] exclusion, Field fieldFrom) {

		// EXCLUIR LOS ATRIBUTOS ESPECIFICADOS
		boolean exclude = false;

		if (exclusion != null && exclusion.length > 0) {

			for (int i = 0; i < exclusion.length; i++) {

				if (exclusion[i] == fieldFrom.getName()) {

					exclude = true;

				}

			}

		}

		return exclude;

	}

	/**
	 * Método para evaluar si un campo está dentro de un Arreglo de Mapeos
	 * 
	 * @param           Map<String, String> mapping, key con el nombre del atributo
	 *                  del objeto de origen y value con el nombre del atributo del
	 *                  objeto de destino.
	 * @param fieldFrom Objeto Field correspondiente al Campo a ser Evaluado para
	 *                  Excluir
	 * @return Map<String, Object> mapping, key con el nombre del valor y value con
	 *         el valor.
	 * @author G. González
	 * 
	 */
	private static Map<String, Object> mapear(Map<String, String> mapping, Field fieldFrom) {

		boolean customMapping = false;

		Map<String, Object> mapeoFinal = new HashMap<>();

		mapeoFinal.put(CUSTOM_MAPPING, false);

		String fieldToMapped = null;

		if (mapping != null) {

			fieldToMapped = mapping.get(fieldFrom.getName());

			if (fieldToMapped != null && !("".equalsIgnoreCase(fieldToMapped))) {

				customMapping = true;

				mapeoFinal.put(CUSTOM_MAPPING, customMapping);

				mapeoFinal.put("fieldToMapped", fieldToMapped);

			}

		}

		return mapeoFinal;

	}

	/**
	 * Método para Castear Listas de Entities con Listas de DTOs o Viceversa
	 * 
	 * @param fieldFrom    Objeto Field correspondiente al Campo Origen
	 * @param fieldto      Objeto Field correspondiente al Campo Destino
	 * @param              Map<Object, Object> mapping, key con el nombre del objeto
	 *                     de origen y value con el nombre del objeto de destino.
	 * @param objFieldFrom Objeto con los Valores que contiene el Objeto Origen
	 * @return List<?> Lista Casteada y con Valores del Objeto Origen Migrados al
	 *         Objeto Destino
	 * @author G. González
	 * 
	 */
	private static List<?> validarListasEntityDTO(Field fieldFrom, Field fieldto, Map<Object, Object> objetos,
			Object objFieldFrom) {

		Type typeFrom = fieldFrom.getGenericType();

		Type typeTo = fieldto.getGenericType();

		List<?> objFieldFromDestino = null;

		try {

			if (typeFrom instanceof ParameterizedType && typeTo instanceof ParameterizedType) {

				ParameterizedType pTypeFrom = (ParameterizedType) typeFrom;

				Type[] arrFrom = pTypeFrom.getActualTypeArguments();

				Class<?> clzzFrom = (Class<?>) arrFrom[0];

				ParameterizedType pTypeTo = (ParameterizedType) typeTo;

				Type[] arrTo = pTypeTo.getActualTypeArguments();

				Class<?> clzzTo = (Class<?>) arrTo[0];

				if (clzzFrom.getSimpleName().replace(ENTITY, "").replace(DTO, "")
						.equalsIgnoreCase(clzzTo.getSimpleName().replace(ENTITY, "").replace(DTO, ""))) {

					List<?> l = getList((ArrayList<?>) objFieldFrom, clzzTo, objetos);

					objFieldFromDestino = l;

				}

			}

		} catch (Exception e) {

			logger.error(ERROR_LISTA);

			throw new ClassCastException(ERROR_LISTA);

		}

		return objFieldFromDestino;

	}

	/**
	 * Método para Castear Objetos de Entities con Objetos de DTOs o Viceversa
	 * 
	 * @param fieldFrom    Objeto Field correspondiente al Campo Origen
	 * @param fieldto      Objeto Field correspondiente al Campo Destino
	 * @param              Map<Object, Object> mapping, key con el nombre del objeto
	 *                     de origen y value con el nombre del objeto de destino.
	 * @param objFieldFrom Objeto con los Valores que contiene el Objeto Origen
	 * @return Object Objeto Casteado y con Valores del Objeto Origen Migrados al
	 *         Objeto Destino
	 * @author G. González
	 * 
	 */
	private static Object validarEntityDTO(Field fieldFrom, Field fieldto, Map<Object, Object> objetos,
			Object objFieldFrom) {

		Object objFieldFromDestino = null;

		try {

			if (fieldFrom.getType().getSimpleName().replace(ENTITY, "").replace(DTO, "")
					.equalsIgnoreCase(fieldto.getType().getSimpleName().replace(ENTITY, "").replace(DTO, ""))) {

				Object castTo = Class.forName(fieldto.getType().getName()).newInstance();

				if (objFieldFrom != null) {

					if (!objetos.containsKey(objFieldFrom)) {

						copyAllFields(objFieldFrom, castTo, null, null, objetos);

						objFieldFromDestino = castTo;

					} else {

						objFieldFromDestino = objetos.get(objFieldFrom);

					}

				}

			} else {

				logger.error("Verificar tipo de dato para el atributo field [" + fieldFrom.getName()
						+ "] en el objeto fr origen y destinos. Tipo de dato es distinto en los objetos.");

				throw new ClassCastException("Verificar tipo de dato para el atributo field [" + fieldFrom.getName()
						+ "] en el objeto fr origen y destinos. Tipo de dato es distinto en los objetos.");

			}

		} catch (Exception e) {

			logger.error("Objeto No Pudo Ser Generado por Problemas de Casteo.");

			throw new ClassCastException("Objeto No Pudo Ser Generado por Problemas de Casteo.");

		}

		return objFieldFromDestino;

	}

	/**
	 * Método para Obtener Campo Destino Dentro del Arreglo de Mappings
	 * 
	 * @param clazztO       Clase Genérica Donde se Realizará la Búsqueda.
	 * @param fieldToMapped Objeto String que Corresponde al Campo a Mapear.
	 * @return Field Campo Destino a Mapear.
	 * @author G. González
	 * @throws NoSuchFieldException
	 * 
	 */
	private static <T> Field obtenerCampoDestinoWithMapping(Class<T> clazztO, String fieldToMapped)
			throws NoSuchFieldException {

		try {

			return clazztO.getDeclaredField(fieldToMapped);

		} catch (NoSuchFieldException ex) { // En caso de no ubicar el campo ir a la Super Clase

			return clazztO.getSuperclass().getDeclaredField(fieldToMapped);
		}

	}

	/**
	 * Método para Obtener Campo Destino Fuera del Arreglo de Mappings
	 * 
	 * @param clazztO       Clase Genérica Donde se Realizará la Búsqueda.
	 * @param fieldToMapped Objeto String que Corresponde al Campo a Mapear.
	 * @return Field Campo Destino a Mapear.
	 * @author G. González
	 * @throws NoSuchFieldException
	 * 
	 */
	private static <T> Field obtenerCampoDestinoWithoutMapping(Class<T> clazztO, Field fieldFrom)
			throws NoSuchFieldException {

		try {

			return clazztO.getDeclaredField(fieldFrom.getName().trim());

		} catch (NoSuchFieldException ex) { // En caso de no ubicar el campo ir a la Super Clase

			return clazztO.getSuperclass().getDeclaredField(fieldFrom.getName().trim());

		}

	}

	/**
	 * Método que Permite Mapear y Castear Listas de un Tipo a Listas de Otro Tipo
	 * 
	 * @param objectFieldfrom Objeto ArrayList de Tipo Genérico que contiene los
	 *                        valores a mapear
	 * @param c               Clase Genérica que Corresponde al Tipo de Dato Destino
	 *                        del Casteo y que Contiene los Atributos Destinos de
	 *                        Casteo.
	 * @param objs            Objeto de Tipo de Map<String, String> que Corresponde
	 *                        a un Arreglo con el Nombre de los Campos a Castear.
	 * @return List<T> Lista de Tipo Genérico Resultante.
	 * @author G. León
	 * @throws Exception
	 * 
	 */
	private static <T> List<T> getList(ArrayList<?> objFieldFrom, Class<T> c, Map<Object, Object> objs) {

		List<T> lstTo = new ArrayList<>();

		try {

			for (Object oFrom : (ArrayList<?>) objFieldFrom) {

				Object castTo = Class.forName(c.getName()).newInstance();

				if (!objs.containsKey(objFieldFrom)) {

					copyAllFields(oFrom, castTo, null, null, objs);

					lstTo.add(c.cast(castTo));

				} else {

					lstTo.add(c.cast(objs.get(objFieldFrom)));

				}

			}

		} catch (Exception e) {

			logger.error(ERROR_LISTA);

			throw new ClassCastException(ERROR_LISTA);

		}

		return lstTo;

	}

	/**
	 * Método que Permite Obtener Todos los Atributos de una Clase
	 * 
	 * @param aClass Clase Genérica a ser Evaluada.
	 * @return List<Field> Lista de Field con los Campos Obtenidos.
	 * @author G. León
	 * 
	 */
	private static List<Field> getAllModelFields(Class<?> aClass) {

		List<Field> fields = new ArrayList<>();

		do {

			Collections.addAll(fields, aClass.getDeclaredFields());

			aClass = aClass.getSuperclass();

		} while (aClass != null);

		return fields;

	}

}
