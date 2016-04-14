import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getStudente")
public class StudenteControllerRequest extends HttpServlet {
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
		
		request.setAttribute("NOME", nome);
		request.setAttribute("COGNOME", cognome);
		request.setAttribute("MATRICOLA", matricola);
		request.setAttribute("DATA", data);
		request.setAttribute("INDIRIZZO", indirizzo);
		request.setAttribute("ADDRESS", address);
		request.setAttribute("HOST", host);
		request.setAttribute("USERAGENT", userAgent);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/student.jsp");
		rd.forward(request, response);
		return;
	
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
