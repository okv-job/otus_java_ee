package ru.korytnikov.oleg.listeners;

import ru.korytnikov.oleg.dao.UserDaoImpl;
import ru.korytnikov.oleg.models.User;
import ru.korytnikov.oleg.models.Users;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class InitListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Initialized");
        File file = new File("/home/okv/db");
        Users users = null;
        if (!file.exists()){
            users = new Users();
            List<User> tempUsers = new ArrayList<>();
            tempUsers.add(new User("Korytnikov","Oleg","Chudnovskogo", 1));
            tempUsers.add(new User("Korytnikov","Alexandr","Kalinina", 2));
            tempUsers.add(new User("Kormanovskaya","Anastasya","Chudnovskogo", 3));
            tempUsers.add(new User("Ermachkov","Aleksey","Kalinina", 4));
            tempUsers.add(new User("Speranskiy","Alexandr","Bolshevikov", 5));
            tempUsers.add(new User("Ermachkova","Ludmila","Kalinina", 6));
            users.setUsers(tempUsers);
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(users, file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                users = (Users) jaxbUnmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        UserDaoImpl dao = new UserDaoImpl();
        dao.init(users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Destroyed");
        UserDaoImpl dao = new UserDaoImpl();
        dao.destroy();
    }
}
