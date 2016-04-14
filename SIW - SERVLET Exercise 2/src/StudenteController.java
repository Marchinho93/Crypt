import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getStudente")
public class StudenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// gestione della RICHIESTA

		// leggo i parametri
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String matricola = request.getParameter("matricola");
		String dataUnconv = request.getParameter("data");
		Date date = stringToDate(dataUnconv);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String data =format.format(date);
		
		String indirizzo = request.getParameter("indirizzo");
		// leggo (alcune) intestazioni http della richiesta
		String address = (String)request.getRemoteAddr();
		String host = (String)request.getRemoteHost();
		String userAgent = request.getHeader("User-Agent");
		
		String path = "C:/accessi.txt";
		BufferedWriter log = new BufferedWriter(new FileWriter(path));
		log.write(nome +" - "+cognome +" - "+matricola+" - "+" - "+indirizzo);
		log.newLine();
		log.close();
		
		// gestione della RISPOSTA

		// preparo il tipo (HTML)
		response.setContentType("text/html");

		// preparo un oggetto su cui scrivere la risposta
		PrintWriter out = response.getWriter();

		
		// scrivo il corpo
		out.println("<!DOCTYPE html>"); out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\" />");
		out.println("<title>Show Student</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Dati Studente</h1>");
		out.println("<ul>");
		out.println("<li>Nome: <b>"+nome+"</b></li>");
		out.println("<li>Cognome: <b>"+cognome+"</b></li>");
		out.println("<li>Matricola: <b>"+matricola+"</b></li>");
		out.println("<li>Data: <b>"+data+"</b></li>");
		out.println("<li>Indirizzo: <b>"+indirizzo+"</b></li>");
		out.println("</ul>");
		out.println("<h2>Altri dati relativi alla richiesta</h2>");
		out.println("<br />IP: <b>"+address+"</b>");
		out.println("<br />Host: <b>"+host+"</b>");
		out.println("<br />User Agent: <b>"+userAgent+"</b>");
		out.println("</body>\n</html> ");
		
	
	}
	
	public static Date stringToDate(String data){
		String dayS = "",monthS = "",yearS = "";
		int day = 0,month = 0,year = 0;
		dayS = data.substring(0, 2);
		monthS = data.substring(3, 5);
		yearS = data.substring(6,10);
		
		System.out.println(dayS + monthS + yearS);
			day = Integer.parseInt(dayS.toString());
			month = Integer.parseInt(monthS.toString());
			year = Integer.parseInt(yearS.toString());
			
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, day);
			c.set(Calendar.MONTH, month-1);
			c.set(Calendar.YEAR, year);
			
			Date datanew = new Date();
			datanew = c.getTime();
			
			
			return datanew;
	}
}
