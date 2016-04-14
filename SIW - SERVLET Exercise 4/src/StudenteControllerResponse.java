import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/printStudente")
public class StudenteControllerResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
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
		out.println("<li>Nome: <b>"+request.getAttribute("NOME")+"</b></li>");
		out.println("<li>Cognome: <b>"+request.getAttribute("COGNOME")+"</b></li>");
		out.println("<li>Matricola: <b>"+request.getAttribute("MATRICOLA")+"</b></li>");
		out.println("<li>Data: <b>"+request.getAttribute("DATA")+"</b></li>");
		out.println("<li>Indirizzo: <b>"+request.getAttribute("INDIRIZZO")+"</b></li>");
		out.println("</ul>");
		out.println("<h2>Altri dati relativi alla richiesta</h2>");
		out.println("<br />IP: <b>"+request.getAttribute("ADDRESS")+"</b>");
		out.println("<br />Host: <b>"+request.getAttribute("HOST")+"</b>");
		out.println("<br />User Agent: <b>"+request.getAttribute("USERAGENT")+"</b>");
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
