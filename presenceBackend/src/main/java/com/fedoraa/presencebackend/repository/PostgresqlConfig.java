package com.fedoraa.presencebackend.repository;

public class PostgresqlConfig {
    public static final String url = System.getenv("DB_URL"); // Remplacez par l'URL de votre base de données
    public static final String user = System.getenv("DB_USER");         // Remplacez par votre utilisateur
    public static final String password = System.getenv("DB_PASSWORD");     // Remplacez par votre mot de passe
}
