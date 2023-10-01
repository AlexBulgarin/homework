package by.sep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;


@WebServlet(name = "VisitsCounterServlet", urlPatterns = "/visitscounter")
public class VisitsCounterServlet extends HttpServlet {
    private int visitsCounter;
    File counterFile = new File("/d/work/homework/homework1/src/main/resources/counter.txt");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            if (counterFile.createNewFile()) {
                visitsCounter = 0;
            } else {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(counterFile))) {
                    visitsCounter = Integer.parseInt(bufferedReader.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        visitsCounter++;
        try (FileWriter fileWriter = new FileWriter(counterFile)) {
            fileWriter.write(String.valueOf(visitsCounter));
            fileWriter.flush();
        }
        String title = "Total visits - ";
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>VisitsCounterServlet</title></head>");
        out.println("<body><h1>" + title + visitsCounter + "</h1>");
        out.println("</body></html>");
    }
}
