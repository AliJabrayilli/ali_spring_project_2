package com.ali_spring_project_2.com.ali_spring_project_2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity // Убедитесь, что аннотация присутствует
@Getter
@Setter
@Table(name = "products") // Указывает имя таблицы
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private Long id;

    @Column(nullable = false)
    private String name; // Название продукта

    @Column(nullable = false)
    private String category; // Категория продукта

    @Column(nullable = false)
    private int stock; // Количество на складе


    @Column(nullable = false)
    private String imagePath;
    @Column(name = "sales_per_day", nullable = false)
    private int salesPerDay; // Продажи в день

    @Column(name = "sales_per_month", nullable = false)
    private int salesPerMonth; // Продажи в месяц

    @Column(nullable = false)
    private double rating; // Рейтинг продукта

    @Column(nullable = false)
    private int ratedCount; // Рейтинг количество

    @Column(nullable = false)
    private int sales; // Общие продажи

    @Column(nullable = false)
    private double revenue; // Доход

    @Column(nullable = false)
    private double price; // Доход

    @Column(nullable = false)
    private double sale; // Скидка

    @Column(nullable = false)
    private boolean fastDelivery;

    @Column(name = "last_update")
    private LocalDate lastUpdate; // Дата последнего обновления

    @Column(nullable = false)
    private String description; // Доход

    @ManyToOne // Связь многие-к-одному с пользователем
    @JoinColumn(name = "user_id", nullable = false) // Имя столбца, ссылающегося на пользователя
    private User user;

    public Product() {
    }

    public Product(Long id, String name, String category, int stock, String imagePath, int salesPerDay, int salesPerMonth, double rating, int ratedCount, int sales, double revenue, double price, double sale, boolean fastDelivery, LocalDate lastUpdate, String description, User user) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.imagePath = imagePath;
        this.salesPerDay = salesPerDay;
        this.salesPerMonth = salesPerMonth;
        this.rating = rating;
        this.ratedCount = ratedCount;
        this.sales = sales;
        this.revenue = revenue;
        this.price = price;
        this.sale = sale;
        this.fastDelivery = fastDelivery;
        this.lastUpdate = lastUpdate;
        this.description = description;
        this.user = user;
    }

    public Product(String name, String category, int stock, double price, String description, User user) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.user = user; // Assign the user
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getSalesPerDay() {
        return salesPerDay;
    }

    public void setSalesPerDay(int salesPerDay) {
        this.salesPerDay = salesPerDay;
    }

    public int getSalesPerMonth() {
        return salesPerMonth;
    }

    public void setSalesPerMonth(int salesPerMonth) {
        this.salesPerMonth = salesPerMonth;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatedCount() {
        return ratedCount;
    }

    public void setRatedCount(int ratedCount) {
        this.ratedCount = ratedCount;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public boolean isFastDelivery() {
        return fastDelivery;
    }

    public void setFastDelivery(boolean fastDelivery) {
        this.fastDelivery = fastDelivery;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}