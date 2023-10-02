package by.sep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;


@WebServlet(name = "VisitsCounter", urlPatterns = "/visitscounter.html")
public class VisitsCounterServlet extends HttpServlet {
    private AtomicInteger visitsCounter;
    File counterFile = new File("/d/work/homework/homework1/" +
            "src/main/java/by/sep/counter.txt");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            if (counterFile.createNewFile()) {
                visitsCounter = new AtomicInteger(0);
            } else {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(counterFile))) {
                    visitsCounter = new AtomicInteger(Integer.parseInt(bufferedReader.readLine()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        visitsCounter.incrementAndGet();
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
