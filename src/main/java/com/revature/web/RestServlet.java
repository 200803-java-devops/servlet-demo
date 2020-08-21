package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/*")
public class RestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        NamesResource resource = new NamesResource(req.getPathInfo());
        int id = resource.getId();
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(NameDao.findById(id-1)));
    }

    private class NamesResource {
        private Pattern regExResourceAll = Pattern.compile("/names");
        private Pattern regExResourceId = Pattern.compile("/names/([0-9]*)");
        private int id;

        public NamesResource(String path) throws ServletException {
            Matcher matcher = regExResourceId.matcher(path);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
                return;
            }

            matcher = regExResourceAll.matcher(path);
            if (matcher.find()) {
                return;
            }

            throw new ServletException("Invalid URL");
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}