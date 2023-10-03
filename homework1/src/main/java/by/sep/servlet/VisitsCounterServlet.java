package by.sep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "VisitsCounter", urlPatterns = "/visitscounter.html")
public class VisitsCounterServlet extends HttpServlet {
    private AtomicInteger visitsCounter;
    File counterFile = new File("/d/work/homework/homework1/" + "src/main/java/by/sep/counter.txt");

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
        String text = "Total visits - " + visitsCounter.incrementAndGet();
        try (FileWriter fileWriter = new FileWriter(counterFile)) {
            fileWriter.write(String.valueOf(visitsCounter));
            fileWriter.flush();
        }
        resp.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(500, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 48));
        graphics.setColor(Color.GREEN);
        graphics.drawString(text, 100, 100);
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        ImageIO.write(image, "jpeg", servletOutputStream);
    }
}