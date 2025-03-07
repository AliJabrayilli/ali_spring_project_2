package com.ali_spring_project_2.com.ali_spring_project_2.services;


import com.ali_spring_project_2.com.ali_spring_project_2.models.Product;
import com.ali_spring_project_2.com.ali_spring_project_2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private final String uploadDir = "C:\\Users\\user\\Documents\\GitHub\\mehebbet_spring_project_5\\mehebbet_spring_project_5\\uploads/";

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        // Define the upload directory (you might want to use an absolute path here)
        File uploadDirectory = new File(uploadDir);

        // Create the upload directory if it doesn't exist
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs(); // Creates the directory, including any necessary parent directories
        }

        // Check if the image file is not empty
        if (imageFile != null && !imageFile.isEmpty()) {
            // Create a unique filename for the image to avoid overwriting existing files
            String originalFilename = imageFile.getOriginalFilename();
            String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename; // e.g., 1634092095671_image.jpg

            // Define the full path for saving the image
            File imageFilePath = new File(uploadDirectory, uniqueFilename);

            // Save the image file to the specified path
            imageFile.transferTo(imageFilePath);

            // Set the image path in the product entity (use relative path if needed)
            product.setImagePath(uniqueFilename); // Save only the filename or the relative path if preferred
        } else {
            throw new IOException("Image file is empty or not provided");
        }

        // Save the product entity to the database
        return productRepository.save(product);
    }


    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId); // Получаем товары по ID пользователя
    }

    public List<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable); // Получаем страницу продуктов
        return productPage.getContent(); // Возвращаем содержимое страницы
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null); // Получаем продукт по ID
    }

    public int getTotalSales() {
        List<Product> products = productRepository.findAll();
        return products.stream().mapToInt(Product::getSales).sum(); // Суммирует общие продажи
    }

    public double getTotalRevenue() {
        List<Product> products = productRepository.findAll();
        return products.stream().mapToDouble(Product::getRevenue).sum(); // Суммирует доход
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}