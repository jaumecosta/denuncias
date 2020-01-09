package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios(String name, String dni, String birthdate) {
        File ficheroUsuarios = new File("D:/usuarios.txt");
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader stream = new BufferedReader(new FileReader(ficheroUsuarios))) {
            while (stream.ready()) {
                String linea = stream.readLine();
                String[] lineSplit = linea.split(",");
                Usuario usuario;
                usuario = new Usuario(name, dni, birthdate);
                usuario.setName(lineSplit[0]);
                usuario.setDni(lineSplit[1]);
                usuario.setBirthdate(lineSplit[2]);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @GetMapping("usuarios/{name}/{dni}/{birthdate}")
    public Boolean crearUsuario(@PathVariable("name") String name, @PathVariable("dni") String dni, @PathVariable("birthdate") String birthdate) throws IOException {
        File fichero = new File("D:\\JAVA\\Spring_origin\\Aplicacion_usuario_Objetos_APIendpointFase1\\src\\main\\resources\\datos.txt");
        try (FileWriter fw = new FileWriter(fichero, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter salida = new PrintWriter(bw);){
            Usuario usuario;
            usuario = new Usuario(name, dni, birthdate);
            salida.print("Nombre " + usuario.setName(name) + ", ");
            salida.print("dni " + usuario.setDni(dni) + ", ");
            salida.print("cumpleaños " + usuario.setBirthdate(birthdate));
        } catch (IOException e) {
            e.printStackTrace();
            //exception handling left as an exercise for the reader
        }
        return true;
    }
}
