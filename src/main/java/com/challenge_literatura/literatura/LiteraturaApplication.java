package com.challenge_literatura.literatura;

import com.challenge_literatura.literatura.Modelo.Libro;
import com.challenge_literatura.literatura.Servicio.ApiGutendex;
import com.challenge_literatura.literatura.Servicio.Menus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication {
	static Scanner scanner = new Scanner(System.in);
	static List<Libro> librosRegistrados = new ArrayList<>();
	public static void main(String[] args) throws IOException, InterruptedException {
		ApiGutendex api = new ApiGutendex();
		int opcion;
		do {
			Menus.mostrarMenuPrincipal();
			opcion = Integer.parseInt(scanner.nextLine());

			switch (opcion) {
				case 1 -> {
					System.out.print("Ingrese término de búsqueda: ");
					String busqueda = scanner.nextLine();
					List<Libro> resultados = api.buscarLibro(busqueda);
					if (resultados.isEmpty()) {
						System.out.println("No se encontraron resultados.");
					} else {
						librosRegistrados.addAll(resultados);
						resultados.forEach(System.out::println);
					}
				}
				case 2 -> {
					if (librosRegistrados.isEmpty()) {
						System.out.println("No hay libros registrados aún.");
					} else {
						librosRegistrados.forEach(System.out::println);
					}
				}
				case 3 -> {
					if (librosRegistrados.isEmpty()) {
						System.out.println("No hay libros registrados.");
					} else {
						librosRegistrados.stream()
								.flatMap(l -> l.authors.stream())
								.distinct()
								.forEach(System.out::println);
					}
				}
				case 4 -> {
					System.out.print("Ingrese el año: ");
					int año = Integer.parseInt(scanner.nextLine());
					librosRegistrados.stream()
							.flatMap(l -> l.authors.stream())
							.filter(a -> a.birthYear <= año && (a.deathYear == null || a.deathYear > año))
							.distinct()
							.forEach(System.out::println);
				}
				case 5 -> {
					System.out.print("Ingrese código de idioma (ej: en, es, fr): ");
					String idioma = scanner.nextLine();
					librosRegistrados.stream()
							.filter(l -> l.languages.contains(idioma))
							.forEach(System.out::println);
				}
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Opción inválida.");
			}
		} while (opcion != 0);
	}
		}