package cl.crojas.blog.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.crojas.blog.utils.exceptions.SimpleErrorHandler;

/**
 * Clase con utilidades generales a la aplicación
 * 
 * @author Christian Rojas N.
 *
 */
public class Utils {

	private static final Logger log = Logger.getLogger(Utils.class);

	private static final String YYYY = "yyyy";
	private static final String MM = "MM";
	private static final String DOT = ".";
	public static final String GUION = "-";

	private static final String PASS_PATT_BLANK = "\\s"; // Espacio en blanco

	// El Dígito Verificador del RUT debe ser K Mayúscula o Minúscula o un valor del
	// 0 al 9
	private static final String PATT_DV_RUT = "[^k-kK-K0-9]+";

	private static final String FILENAME_PATT = "[a-zA-Z0-9_ -]*";

	public static final String VERSION_POM = "java:global/blog-ear/blog-services-0/0/1-SNAPSHOT/";

	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

	public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;

	private Utils() {
		super();
	}

	/**
	 * Método que permite dar formato a una fecha.
	 * 
	 * @param dateAtr fecha a aplicar formato.
	 * @param format  formato a aplicar fecha.
	 * @return String fecha con formato.
	 * @author Christian Rojas N.
	 * 
	 */
	public static String formatDateToString(Date dateAtr, String format) {

		String date = null;

		if (dateAtr != null && !isNullOrEmpty(format)) {

			SimpleDateFormat sdf = new SimpleDateFormat(format);

			date = sdf.format(dateAtr);

			try {

				sdf.parse(date);

			} catch (ParseException e) {

				log.error("Error al parsear fecha: " + dateAtr + ". Con formato: " + format, e);

				return null;

			}

		}

		return date;

	}

	/**
	 * Método que permite dar formato a una fecha y retorna una Fecha.
	 * 
	 * @param dateAtr fecha a aplicar formato.
	 * @param format  formato a aplicar fecha.
	 * @return Date fecha con formato.
	 * @author Christian Rojas N.
	 * 
	 */
	public static Date formatDateToDate(Date dateAtr, String format) {

		String date = null;

		Date dateFinal = null;

		if (dateAtr != null && !isNullOrEmpty(format)) {

			SimpleDateFormat sdf = new SimpleDateFormat(format);

			date = sdf.format(dateAtr);

			try {

				dateFinal = sdf.parse(date);

			} catch (ParseException e) {

				log.error("Error al parsear fecha: " + dateAtr + ". Con formato: " + format, e);

				return null;

			}

		}

		return dateFinal;

	}

	public static int getNumDayOfWeek(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_WEEK);

	}

	public static String getDayOfWeek(Date date) {

		String day = null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int numDay = cal.get(Calendar.DAY_OF_WEEK);

		switch (numDay) {
		case 1:
			day = "Domingo ";
			break;
		case 2:
			day = "Lunes ";
			break;
		case 3:
			day = "Martes ";
			break;
		case 4:
			day = "Miercoles ";
			break;
		case 5:
			day = "Jueves ";
			break;
		case 6:
			day = "Viernes ";
			break;
		case 7:
			day = "Sábado ";
			break;
		default:
			day = null;
			break;
		}

		return day;

	}

	public static String getMonthOfDate(Date date) {

		String month = null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int numMonth = cal.get(Calendar.MONTH);

		switch (numMonth) {
		case 0:
			month = " Enero ";
			break;
		case 1:
			month = " Febrero ";
			break;
		case 2:
			month = " Marzo ";
			break;
		case 3:
			month = " Abril ";
			break;
		case 4:
			month = " Mayo ";
			break;
		case 5:
			month = " Junio ";
			break;
		case 6:
			month = " Julio ";
			break;
		case 7:
			month = " Agosto ";
			break;
		case 8:
			month = " Septiembre ";
			break;
		case 9:
			month = " Octubre ";
			break;
		case 10:
			month = " Noviembre ";
			break;
		case 11:
			month = " Diciembre ";
			break;
		default:
			month = null;
		}

		return month;

	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean isNullOrEmpty(Long number) {
		return (number == null || number.longValue() == 0L);
	}

	public static boolean isNullOrEmpty(Integer number) {
		return (number == null || number.intValue() == 0);
	}

	/**
	 * Método para validar si una colección es nula o está vacía.
	 * 
	 * @param c Collection a evaluar
	 * @return true si está nula o vacía, false en caso contrario
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean isNullOrEmpty(final Collection<?> c) {
		return (c == null || c.isEmpty());
	}

	/**
	 * Valida si un BigInteger es nulo o está vacío.
	 * 
	 * @param bi BigInteger a evaluar
	 * @return true si está nulo o vacío, false en caso contrario
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean isNullOrEmpty(final BigInteger bi) {
		return (bi == null || bi.longValue() == 0L);
	}

	/**
	 * Método que permite obtener fecha actual sólo con formato dd-MM-YYYY
	 * 
	 * @return Date Fecha Actual del Sistema
	 * @author Christian Rojas N.
	 * 
	 */
	public static Date getFechaActualSinTiempo() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}

	/**
	 * Método que determina si una cadena es número
	 * 
	 * @param String cadena a evaluar
	 * @return si es numérico o no
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean isNumeric(String cadena) {

		try {

			Integer.parseInt(cadena);

			return true;

		} catch (NumberFormatException nfe) {

			return false;

		}

	}

	/**
	 * Método que determina si un correo electrónico es válido
	 * 
	 * @param String email a evaluar
	 * @return boolean si es válido o no
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean isValidEmailAddress(String email) {

		boolean result = true;

		try {

			InternetAddress emailAddr = new InternetAddress(email);

			emailAddr.validate();

		} catch (AddressException ex) {

			result = false;

		}

		return result;

	}

	/**
	 * Método que Agrega Días a una Fecha
	 * 
	 * @param Date fecha a modificar
	 * @param      long dias a agregar
	 * @return Date fecha resultante
	 * @author Christian Rojas N.
	 * 
	 */
	public static Date addDaysToDate(Date fecha, long dias) {

		LocalDateTime localDateTime = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		// Sumar dias
		localDateTime = localDateTime.plusDays(dias);

		// Convertir a Date
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

	}

	/**
	 * Retorna un String vacío si texto es null
	 * 
	 * @param texto a evaluar
	 * @return String Cadena resultante
	 * @author Christian Rojas N.
	 * 
	 */
	public static String emptyIfNull(String texto) {

		String retorno;

		try {

			retorno = isNullOrEmpty(texto) ? "" : texto;

		} catch (NullPointerException e) {

			retorno = "";

		}

		return retorno;

	}

	/**
	 * Método que devuelve Fecha desde un string
	 * 
	 * @param String date con fecha a convertir
	 * @param String format con formato SimpleDateFormat
	 * @return Date con fecha
	 * @author Christian Rojas N.
	 * 
	 */
	public static Date getDateFromString(String date, String format) {

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(format);

		Date fecha = null;

		try {

			fecha = formatoDelTexto.parse(date);

		} catch (Exception ex) {

			fecha = null;

		}

		return fecha;

	}

	public static Integer getYearFromDate(Date date) {

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(YYYY);

		Integer year = null;

		try {

			year = Integer.valueOf(formatoDelTexto.format(date));

		} catch (Exception ex) {

			year = null;

		}

		return year;

	}

	public static Integer getMonthFromDate(Date date) {

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(MM);

		Integer month = null;

		try {

			month = Integer.valueOf(formatoDelTexto.format(date));

		} catch (Exception ex) {

			month = null;

		}

		return month;

	}

	public static Date getISOStringDatetoDate(String date) {
		Date fecha = null;
		try {
			OffsetDateTime odt = OffsetDateTime.parse(date);
			fecha = Date.from(odt.toInstant());
		} catch (Exception e) {
			log.error("getISOStringDatetoDate(): " + e.getMessage(), e);
			fecha = null;
		}
		return fecha;
	}

	/**
	 * Método que convierte un String a XML
	 * 
	 * @param xml String con la cadena a convertir
	 * @return Document Objeto que contiene el XML, de lo contrario, si no pudo
	 *         convertirlo, devuelve null
	 * @author Christian Rojas N.
	 * 
	 */
	public static Document convertirAXML(String xml) {

		Document doc = null;

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setValidating(false);

			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();

			builder.setErrorHandler(new SimpleErrorHandler());

			// El Método "parse" también valida XML, lanzará una excepción si el mismo está
			// malformado
			doc = builder.parse(new InputSource(new StringReader(xml)));

		} catch (Exception e) {

			doc = null;

		}

		return doc;

	}

	/**
	 * Método que devuelve XML desde un string
	 * 
	 * @param xml String con la cadena a convertir
	 * @return boolean True indica que pudo convertirlo, de lo contrario, False
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean getXMLFromString(String xml) {

		return (convertirAXML(xml) != null);

	}

	/**
	 * Método que devuelve la cantidad de días entre dos fechas
	 * 
	 * @param Date Fecha de Inicio
	 * @param Date Fecha de Fin
	 * @return long Cantidad de Días
	 * @author Christian Rojas N.
	 * 
	 */
	public static long getDaysBetweenDates(Date fechaInicio, Date fechaFin) {

		LocalDate ldFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate ldFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return Duration.between(ldFechaInicio.atStartOfDay(), ldFechaFin.atStartOfDay()).toDays();

	}

	/**
	 * Método que convierte un InputStream en String
	 * 
	 * @param InputStream Secuencia de Bytes
	 * @return String Cadena Obtenida de la Conversión
	 * @author Christian Rojas N.
	 * 
	 */
	public static String getStringFromInputStream(InputStream is) {

		StringBuilder sb = new StringBuilder();

		String line;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			while ((line = br.readLine()) != null) {

				sb.append(line);

			}

		} catch (IOException e) {

			return null;

		}

		return sb.toString();

	}

	/**
	 * Método para Obtener el fmContext de un Request
	 * 
	 * @param HttpServletRequest Petición de la Página Web
	 * @return String URL fmContext
	 * @author Christian Rojas N.
	 * 
	 */
	public static String getFmContextUrl(HttpServletRequest request) {

		String scheme = request.getScheme();

		String serverName = request.getServerName();

		int serverPort = request.getServerPort();

		String contextPath = request.getContextPath();

		StringBuilder sbBase = new StringBuilder();

		sbBase.append(scheme.trim());

		sbBase.append("://");

		sbBase.append(serverName);

		sbBase.append(":");

		sbBase.append(serverPort);

		sbBase.append(contextPath);

		sbBase.append("/xsl");

		return sbBase.toString();

	}

	/**
	 * Método que determina si un RUN es válido
	 * 
	 * @param run Parte Entera
	 * @param dv  Dígito Verificador
	 * @return true si es válido. false en caso contrario
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean validarRut(String run, char dv) {

		try {

			int rut = Integer.parseInt(run);

			int m = 0;

			int s = 1;

			for (; rut != 0; rut /= 10) {

				s = (s + rut % 10 * (9 - m++ % 6)) % 11;

			}

			return dv == (char) (s != 0 ? s + 47 : 75);

		} catch (NumberFormatException e) {

			return false;

		}

	}

	/**
	 * Método que determina si un RUN es válido (caso LDAP)
	 * 
	 * @param run Parte Entera
	 * @param dv  Dígito Verificador
	 * @return true si es válido. false en caso contrario
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean validarRutLDAP(String run, char dv) {

		try {

			int rut = Integer.parseInt(run);

			int m = 0;

			int s = 1;

			for (; rut != 0; rut /= 10) {

				s = (s + rut % 10 * (9 - m++ % 6)) % 11;

			}

			boolean respuesta = (dv == (char) (s != 0 ? s + 47 : 75));

			if (!respuesta && dv == '1') {

				respuesta = ('K' == (char) (s != 0 ? s + 47 : 75));

			}

			return respuesta;

		} catch (NumberFormatException e) {

			return false;

		}

	}

	/**
	 * Método que formatear el RUN del Usuario (caso LDAP)
	 * 
	 * @param run RUN del Usuario
	 * @return String con el RUN deseado
	 * @author Christian Rojas N.
	 * 
	 */
	public static String obtenerRutLDAP(String run) {

		try {

			String rutFormateado = "";

			char dv = getDVFromRut(run).charAt(0);

			int rut = Integer.parseInt(run.substring(0, run.length() - 1));

			int m = 0;

			int s = 1;

			for (; rut != 0; rut /= 10) {

				s = (s + rut % 10 * (9 - m++ % 6)) % 11;

			}

			boolean respuesta = (dv == (char) (s != 0 ? s + 47 : 75));

			if (!respuesta && dv == '1') {

				rutFormateado = run.substring(0, run.length() - 1) + "K";

			} else {

				rutFormateado = run;

			}

			return rutFormateado;

		} catch (NumberFormatException e) {

			return null;

		}

	}

	/**
	 * Método que valida si un string cumple con un determinado patrón en cuanto a
	 * su composición
	 * 
	 * @param cadena        String a evaluar
	 * @param formatPattern patrón de evaluación
	 * @return boolean True si cumple con el patrón, False en caso contrario
	 * @author A. Cabello
	 * 
	 */
	public static boolean validateStringByPattern(String cadena, String formatPattern) {

		Pattern pat = Pattern.compile(formatPattern);

		Matcher mat = pat.matcher(cadena.trim());

		return mat.find();

	}

	/**
	 * Método que valida si la cadena contiene espacios en blanco
	 * 
	 * @param cadena String a evaluar
	 * @return boolean true si contiene espacios o false si no contiene
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean containsSpacesBlank(String cadena) {
		return validateStringByPattern(cadena, PASS_PATT_BLANK);
	}

	/**
	 * Método que devuelve Rut sin puntos ni guión
	 * 
	 * @param rutCompleto RUT a modificar
	 * @return String con valor de rut limpio
	 * @author Christian Rojas N.
	 * 
	 */
	public static String getRutSinPuntosNiGuion(String rutCompleto) {
		return rutCompleto.replaceAll(PATT_DV_RUT, "").toUpperCase();
	}

	/**
	 * Metodo para añadir puntos y guiones al run de un usuario ciudadano que se
	 * registra, a modo de setear su username
	 * 
	 * @param run
	 * @return
	 */
	public static String formatearRUN(Integer runInt, String dv) {
		StringBuilder runFormateado = new StringBuilder();
		String runInvertido = new StringBuilder().append(runInt).reverse().toString();
		for (int i = 0; i < runInvertido.length(); i++) {
			if (i % 3 == 0 && i > 0) {
				runFormateado.append(DOT);
			}
			runFormateado.append(runInvertido.charAt(i));
		}
		runFormateado.reverse();
		runFormateado.append(GUION + dv);
		runFormateado.trimToSize();
		return runFormateado.toString();
	}

	/**
	 * Método que devuelve DV de un RUT
	 * 
	 * @param rutCompleto RUT a utilizar
	 * @return Long con valor del Dígito Verificador
	 * @author Christian Rojas N.
	 * 
	 */
	public static String getDVFromRut(String rutCompleto) {
		String rut = getRutSinPuntosNiGuion(rutCompleto);
		return rut.substring(rut.length() - 1, rut.length());
	}

	/**
	 * Método que devuelve rut sin DV
	 * 
	 * @param rutCompleto
	 * @return Long con valor de rut
	 */
	public static Integer getRutSinDV(String rutCompleto) {
		String rut = getRutSinPuntosNiGuion(rutCompleto);
		return Integer.parseInt(rut.substring(0, rut.length() - 1));
	}

	/**
	 * Método que devuelve true si el rut es válido o false si no lo es
	 * 
	 * @param run RUT a evaluar
	 * @return true si el rut es válido o false si no lo es
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean evaluateStringRun(String run) {

		try {

			if (containsSpacesBlank(run)) {

				return false;

			}

			String auxRun = getRutSinPuntosNiGuion(run);

			char ch = getDVFromRut(run).charAt(0);

			auxRun = auxRun.substring(0, (auxRun.length() - 1));

			return validarRut(auxRun, ch);

		} catch (Exception e) {

			return false;

		}

	}

	/**
	 * Método que devuelve true si el rut es válido o false si no lo es (caso LDAP)
	 * 
	 * @param run RUT a evaluar
	 * @return true si el rut es válido o false si no lo es
	 * @author Christian Rojas N.
	 * 
	 */
	public static boolean evaluateStringRunLDAP(String run) {

		try {

			if (containsSpacesBlank(run)) {

				return false;

			}

			String auxRun = getRutSinPuntosNiGuion(run);

			char ch = getDVFromRut(run).charAt(0);

			auxRun = auxRun.substring(0, (auxRun.length() - 1));

			return validarRutLDAP(auxRun, ch);

		} catch (Exception e) {

			return false;

		}

	}

	/**
	 * Método que devuelve un valor dentro de un Tag en un XML
	 * 
	 * @param ruta String que contiene la Ruta del Valor a Buscar
	 * @param xml  String correspondiente al XML
	 * @return String con el Valor Obtenido, null en caso de no obtener nada
	 * @author Christian Rojas N.
	 * 
	 */
	public static String getValueTagXML(String ruta, String xml) {

		String respuesta = null;

		try {

			String inicio = "<" + ruta + ">";

			String fin = "</" + ruta + ">";

			respuesta = xml.substring(xml.indexOf(inicio) + inicio.length(), xml.indexOf(fin));

			return respuesta;

		} catch (Exception e) {

			return null;

		}

	}

	/**
	 * Método para convertir de Byte a Base64
	 * 
	 * @param byte[] Arreglo de Bytes a Convertir
	 * @return byte[] Base64 resultante
	 * @author C. Ramírez
	 * 
	 */
	public static byte[] bytesToBase64(byte[] file) {
		return Base64.getEncoder().encode(file);
	}

	/**
	 * Método para convertir un String a Base64
	 * 
	 * @param xml String a Convertir
	 * @return byte[] Base64 resultante
	 * @author C. Ramírez
	 * 
	 */
	public static byte[] getXMLStringToBytes(String xml) {
		return xml.getBytes(CHARSET_UTF_8);
	}

	/**
	 * Método para convertir de Base64 a String
	 * 
	 * @param byte[] Arreglo de Bytes a Convertir
	 * @return String Cadena resultante
	 * @author C. Ramírez
	 * 
	 */
	public static String getXMLBytesToString(byte[] xmlByte) {
		return new String(xmlByte, CHARSET_UTF_8);
	}

	/**
	 * Método para codificar a Base64
	 * 
	 * @param xml String a codificar
	 * @return String String codificado
	 * @author C. Ramírez
	 * 
	 */
	public static String codificarBase64(String xml) {

		String xml64 = "";

		if (xml != null && !xml.isEmpty()) {

			byte[] byteXmlSignedFile = xml.getBytes(CHARSET_UTF_8);

			xml64 = Base64.getEncoder().encodeToString(byteXmlSignedFile);

		}

		return xml64;

	}

	/**
	 * Método Estático para codificar a Base64
	 * 
	 * @param xml String a codificar
	 * @return String String codificado
	 * @author Christian Rojas N.
	 * 
	 */
	public static String codificarBase64Estatico(String xml) {

		String xml64Estatico = "";

		if (xml != null && !xml.isEmpty()) {

			byte[] byteXmlSignedFile = xml.getBytes(CHARSET_UTF_8);

			xml64Estatico = Base64.getEncoder().encodeToString(byteXmlSignedFile);

		}

		return xml64Estatico;

	}

	/**
	 * Método para decodificar a String
	 * 
	 * @param xml String a decodificar
	 * @return String String decodificado
	 * @author C. Ramírez
	 * 
	 */
	public static String decodificarBase64(String xml) {

		String xml64 = "";

		if (xml != null && !xml.isEmpty()) {

			byte[] decoded = Base64.getDecoder().decode(xml);

			xml64 = new String(decoded, CHARSET_UTF_8);

		}

		return xml64;

	}

	/**
	 * Método Estático para decodificar a String
	 * 
	 * @param xml String a decodificar
	 * @return String String decodificado
	 * @author Christian Rojas N.
	 * 
	 */
	public static String decodificarBase64Estatico(String xml) {

		String xml64 = "";

		if (xml != null && !xml.isEmpty()) {

			Decoder decoder = Base64.getDecoder();

			byte[] decoded = decoder.decode(xml);

			xml64 = new String(decoded, CHARSET_UTF_8);

		}

		return xml64;

	}

	/**
	 * Método para convertir de Byte a Base64 String
	 * 
	 * @param byte[] Arreglo de Bytes a Convertir
	 * @return String Base64 resultante
	 * @author Christian Rojas N.
	 * 
	 */
	public static String bytesToBase64String(byte[] file) {
		return Base64.getEncoder().encodeToString(file);
	}

	/**
	 * Método para convertir de Long a Base64 String
	 * 
	 * @param byte[] Arreglo de Bytes a Convertir
	 * @return String Base64 resultante
	 * @author Christian Rojas N.
	 * 
	 */
	public static String longToBase64String(long numero) {
		String num = Long.toString(numero);
		return bytesToBase64String(num.getBytes(Charset.forName("UTF-8")));
	}

	/**
	 * Método para sanitizar una Cadena de Caracteres
	 * 
	 * @param html Cadena de Caracteres a Procesar
	 * @return String Cadena de Caracteres Sanitizada
	 * @author Christian Rojas N.
	 * 
	 */
	public static String sanitizar(String html) {

		if (html == null)
			return html;

		// Caracteres Especiales
		html = html.replaceAll("&Aacute;", "Á");
		html = html.replaceAll("&Eacute;", "É");
		html = html.replaceAll("&Iacute;", "Í");
		html = html.replaceAll("&Oacute;", "Ó");
		html = html.replaceAll("&Uacute;", "Ú");
		html = html.replaceAll("&aacute;", "á");
		html = html.replaceAll("&eacute;", "é");
		html = html.replaceAll("&iacute;", "í");
		html = html.replaceAll("&oacute;", "ó");
		html = html.replaceAll("&uacute;", "ú");
		html = html.replaceAll("&deg;", "°");
		html = html.replaceAll("&ordm;", "°");
		html = html.replaceAll("&ntilde;", "ñ");
		html = html.replaceAll("&Ntilde;", "Ñ");
		html = html.replaceAll("&hellip;", "...");
		html = html.replaceAll("&nbsp;", " ");

		return html;

	}

	/**
	 * Método para sanitizar una Cadena de Caracteres (Sacando las Tildes y la Ñ)
	 * 
	 * @param html Cadena de Caracteres a Procesar
	 * @return String Cadena de Caracteres Sanitizada
	 * @author Christian Rojas N.
	 * 
	 */
	public static String sanitizarSinAcento(String html) {

		if (html == null)
			return html;

		// Caracteres Especiales
		html = html.replaceAll("&Aacute;", "A");
		html = html.replaceAll("&Eacute;", "E");
		html = html.replaceAll("&Iacute;", "I");
		html = html.replaceAll("&Oacute;", "O");
		html = html.replaceAll("&Uacute;", "U");
		html = html.replaceAll("&aacute;", "a");
		html = html.replaceAll("&eacute;", "e");
		html = html.replaceAll("&iacute;", "i");
		html = html.replaceAll("&oacute;", "o");
		html = html.replaceAll("&uacute;", "u");
		html = html.replaceAll("&deg;", "");
		html = html.replaceAll("&ordm;", "");
		html = html.replaceAll("&ntilde;", "ñ");
		html = html.replaceAll("&Ntilde;", "Ñ");
		html = html.replaceAll("&hellip;", "...");
		html = html.replaceAll("&nbsp;", " ");

		return html;

	}

	/**
	 * Método para convertir un Base64 a Bytes
	 * 
	 * @param base64String Cadena Base64 Procesar
	 * @return byte[] Arreglo de Bytes Final
	 * @author Christian Rojas
	 * 
	 */
	public static byte[] base64StringToBytes(String base64String) {
		return Base64.getDecoder().decode(base64String);
	}

	/**
	 * Método para convertir de Objeto a JSON
	 * 
	 * @param obj Objeto a Convertir
	 * @return String JSON Resultante
	 * @author Christian Rojas
	 * 
	 */
	public static String getObjToJSON(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	/**
	 * Método para convertir un XML a HTML
	 * 
	 * @param xml Objeto String que representa el XML a Convertir
	 * @param xsl Objeto String que representa el XSL a usar como Plantilla
	 * @return String HTML Obtenido
	 * @author Christian Rojas N.
	 * 
	 */
	public static String convertHTMLfromXMLandXSL(String xml, String xsl, Map<String, Object> param)
			throws TransformerException {

		String html = null;

		TransformerFactory factory = TransformerFactory.newInstance();

		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

		Templates templates = factory.newTemplates(new StreamSource(new StringReader(xsl)));

		Transformer transformer = templates.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		if (null != param && !param.isEmpty()) {

			param.forEach((k, v) -> {

				transformer.setParameter(k, v);

			});

		}

		Source source = new StreamSource(new StringReader(xml));

		StringWriter writer = new StringWriter();

		Result result = new StreamResult(writer);

		transformer.transform(source, result);

		html = sanitizarHTML(writer.toString());

		return html;

	}

	/**
	 * Método para sanitizar un HTML
	 * 
	 * @param html Objeto String que representa el HTML a Sanitizar
	 * @return String HTML Sanitizado
	 * @author Christian Rojas N.
	 * 
	 */
	private static String sanitizarHTML(String html) {

		if (html == null)
			return html;

		html = html.replaceAll("<br>", "<br/>");

		html = html.replaceAll("<BR>", "<BR/>");

		html = html.replaceAll("<img +([^>]*)[^/]>", "<img $1\"></img>");

		html = html.replaceAll("<IMG +([^>]*)[^/]>", "<IMG $1\"></IMG>");

		html = html.replaceAll("<META *([^>]*)>", "<META $1/>");

		html = fixTag(html, "meta");

		html = fixTag(html, "title");

		html = fixTag(html, "link");

		html = fixTag(html, "script");

		html = fixTag(html, "img");

		html = fixTag(html, "div");

		html = fixTag(html, "ul");

		html = fixTag(html, "p");

		html = fixTag(html, "td");

		html = fixTag(html, "span");

		return html;

	}

	/**
	 * Método para depurar un HTML
	 * 
	 * @param html Objeto String que representa el HTML a Procesar
	 * @return String HTML Depurado
	 * @author Christian Rojas N.
	 * 
	 */
	private static String fixTag(String html, String tag) {

		tag = tag.toLowerCase();

		html = fixTags(html, tag);

		tag = tag.toUpperCase();

		html = fixTags(html, tag);

		return html;

	}

	/**
	 * Método para sacar etiquetas de un HTML
	 * 
	 * @param html Objeto String que representa el HTML a Procesar
	 * @return String HTML Procesado
	 * @author Christian Rojas N.
	 * 
	 */
	private static String fixTags(String html, String tag) {

		html = html.replaceAll("<" + tag + " +([^>]*)/>", "<" + tag + " $1></" + tag + ">");

		html = html.replaceAll("<" + tag + "/>", "<" + tag + "></" + tag + ">");

		return html;

	}

	public static boolean validateFilename(String fileName) {
		Pattern pattern = Pattern.compile(FILENAME_PATT);
		Matcher matcher = pattern.matcher(getFileName(fileName));
		log.debug("validateFilename(): is valid? " + matcher.matches());
		return matcher.matches();
	}

	public static String getFileName(String fileName) {
		if (fileName.lastIndexOf(DOT) != -1 && fileName.lastIndexOf(DOT) != 0)
			return fileName.substring(0, fileName.lastIndexOf(DOT));
		else
			return "";
	}

	public static String replaceDateString(String stringDate) {
		if (stringDate != null && stringDate.length() > 0)
			stringDate = stringDate.replace("\\/", "-");
		return stringDate;
	}

	/**
	 * Limpia el id solicitud de suseso, cuando se envia a los servicios de suseso,
	 * ya que, el id solicitud lo guardamos con un identificador de iteración,
	 * ejemplo: 123, corresponde a iteración 1 y 123-2 corresponde a iteración 2.
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public static String limpiarIdSolicitud(String idSolicitud) {
		if (idSolicitud.contains("-")) {
			String[] corte = idSolicitud.split("-");
			idSolicitud = corte[0];
		}
		return idSolicitud;
	}

	/**
	 * Método usado para obtener la diferencia entre dos Fechas (retorna String)
	 * 
	 * @param fechaMayor Objeto Date correspondiente a la Fecha Hasta (To)
	 * @param fechaMenor Objeto Date correspondiente a la Fecha Desde (From)
	 * @return String Período correspondiente a la Diferencia entre las Fechas
	 * @author Alexis Rivas
	 *
	 */
	public static String diaEntreFechas(Date fechaMayor, Date fechaMenor) {

		// Date to Calendar (FechaMayor)
		Calendar calTo = Calendar.getInstance();

		calTo.setTime(fechaMayor);

		int yearTo = calTo.get(Calendar.YEAR);

		int monthTo = calTo.get(Calendar.MONTH);

		int dayTo = calTo.get(Calendar.DAY_OF_MONTH);

		int hourTo = calTo.get(Calendar.HOUR_OF_DAY);

		int minuteTo = calTo.get(Calendar.MINUTE);

		// Date to Calendar (FechaMenor)
		Calendar calFrom = Calendar.getInstance();

		calFrom.setTime(fechaMenor);

		int yearFrom = calFrom.get(Calendar.YEAR);

		int monthFrom = calFrom.get(Calendar.MONTH);

		int dayFrom = calFrom.get(Calendar.DAY_OF_MONTH);

		int hourFrom = calFrom.get(Calendar.HOUR_OF_DAY);

		int minuteFrom = calFrom.get(Calendar.MINUTE);

		// From
		DateTime from = new DateTime(yearFrom, (monthFrom + 1), dayFrom, hourFrom, minuteFrom);

		// To
		DateTime to = new DateTime(yearTo, (monthTo + 1), dayTo, hourTo, minuteTo);

		// Period
		Period period = new Period(from, to);

		return "P" + period.getYears() + "Y" + period.getMonths() + "M" + period.getDays() + "DT" + period.getHours()
				+ "H" + period.getMinutes() + "M" + period.getSeconds() + "S";

	}

	/**
	 * Método usado para construir el Nombre Completo del Usuario
	 * 
	 * @param nombres         Objeto String correspondiente a los Nombres del
	 *                        Usuario
	 * @param apellidoPaterno Objeto String correspondiente al Apellido Paterno del
	 *                        Usuario
	 * @param apellidoMaterno Objeto String correspondiente al Apellido Materno del
	 *                        Usuario
	 * @return String Nombre Completo del Usuario
	 * @author Christian Rojas N.
	 *
	 */
	public static String construirNombreCompleto(String nombres, String apellidoPaterno, String apellidoMaterno) {

		String nombreCompleto = "";

		if (nombres != null) {

			nombreCompleto = nombres + " ";

		}

		if (apellidoPaterno != null) {

			nombreCompleto = nombreCompleto + apellidoPaterno + " ";

		}

		if (apellidoMaterno != null) {

			nombreCompleto = nombreCompleto + apellidoMaterno;

		}

		return nombreCompleto;

	}

}
