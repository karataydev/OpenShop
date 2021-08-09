package com.karatay.openshop.config;


import com.karatay.openshop.model.*;
import com.karatay.openshop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, CategoryRepository categoryRepository){
        return args -> {
            Role user = new Role(ERole.ROLE_USER);
            Role seller = new Role(ERole.ROLE_SELLER);
            Role admin = new Role(ERole.ROLE_ADMIN);

            roleRepository.saveAll(List.of(user,seller,admin));
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            User emre = new User(
                    "emrem4rff",
                    "marufemre99@gmail.com",
                    encoder.encode("1221"),
                    "Maruf Emre",
                    "Karatay"

            );
            Set<Role> roless = new HashSet<>();
            roless.add(user);
            roless.add(admin);
            emre.setRoles(roless);


            emre.setCart(new Cart());
            userRepository.save(emre);

            Category tech = new Category("Technology","https://m.media-amazon.com/images/I/61s0IaMcKtL.jpg");
            Category books = new Category("Books","https://m.media-amazon.com/images/P/0375842209.01._SCLZZZZZZZ_SX500_.jpg");
            Category clothing = new Category("Clothing", "https://m.media-amazon.com/images/I/71yl+mpx+EL._AC_UY445_.jpg");
            Category home = new Category("Home", "https://m.media-amazon.com/images/I/71l-N2yNe2L._AC_SL1500_.jpg");
            Category cleaning = new Category("Cleaning", "https://m.media-amazon.com/images/I/61kD6JYP0xL._AC_SL1500_.jpg");
            Category automotive = new Category("Automotive", "https://m.media-amazon.com/images/I/71wNWtNb0sL._SL1500_.jpg");

            categoryRepository.saveAll(List.of(tech,books,clothing, home, cleaning, automotive));

            Product tv = new Product("Samsung Tv","Android Smart Tv for your home",100,9000.00,tech,List.of("https://m.media-amazon.com/images/I/61I2BU6N+rL._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/41SCHyNnFlL._AC_.jpg", "https://m.media-amazon.com/images/I/410pBMDy4GL._AC_.jpg"));
            Product iphone = new Product("Iphone 12 64GB, Blue","5G speed. A14 Bionic. Super Retina XDR display. Ceramic Shield. And Night mode on every camera.",50,799.99,tech,List.of("https://m.media-amazon.com/images/I/318k1T47fvL._AC_.jpg", "https://m.media-amazon.com/images/I/51jMLm19LzL._AC_SL1112_.jpg"));
            Product camera = new Product("Canon EOS R6 Full-Frame Mirrorless Camera + RF24-105mm F4-7.1 is STM Lens Kit, Black (4082C022)","High Image Quality features a New 20 Megapixel Full-frame CMOS Sensor\n" +
                    "DIGIC X Image Processor, Expandable to 204800\n" +
                    "High-speed Continuous Shooting of up to 12 fps with Mechanical Shutter and up to 20 fps Electronic (Silent) Shutter\n" +
                    "Dual Pixel CMOS AF covering Approx. 100% Area with 1,053 AF Areas\n" +
                    "Subject tracking of People and Animals using Deep Learning Technology\n" +
                    "Video capture resolution is 2160p",45,2799.00,tech,List.of("https://m.media-amazon.com/images/I/81VZhWiI9FL._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/81c5RZBQuML._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/81vdvuNTbVL._AC_SL1500_.jpg"));
            Product macbook = new Product("2020 Apple MacBook Pro with Apple M1 Chip (13-inch, 8GB RAM, 256GB SSD Storage) - Space Gray","Apple-designed M1 chip for a giant leap in CPU, GPU, and machine learning performance\n" +
                    "Get more done with up to 20 hours of battery life, the longest ever in a Mac\n" +
                    "8-core CPU delivers up to 2.8x faster performance to fly through workflows quicker than ever\n" +
                    "8-core GPU with up to 5x faster graphics for graphics-intensive apps and games\n" +
                    "16-core Neural Engine for advanced machine learning\n" +
                    "8GB of unified memory so everything you do is fast and fluid\n" +
                    "Superfast SSD storage launches apps and opens files in an instant",22,1099.99,tech,List.of("https://m.media-amazon.com/images/I/71an9eiBxpL._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/81zKcC5wJ6L._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/913qQiDRZQL._AC_SL1500_.jpg"));
            Product cloth = new Product("Allegra K Women's Button Down Vintage Polka Dots Dresses Collar Tie Belted Shirt Dress","Button down closure\n" +
                    "Machine Wash\n" +
                    "Button Down, Point Collar, Long sleeves with Button Cuffs, Above the Knee, A-Line, Slightly Stretchy Fabric, Unlined\n" +
                    "Floral Print and Vintage Polka Dots, Tie Belted, Button Placket Shirt Dress brighten your wardrobe with the stylish dress",19,30.99,clothing,List.of("https://m.media-amazon.com/images/I/71yl+mpx+EL._AC_UY550_.jpg", "https://m.media-amazon.com/images/I/71szEVniQ6L._AC_UY445_.jpg", "https://m.media-amazon.com/images/I/61bXrtomBAL._AC_UY445_.jpg"));
            Product sofa = new Product("Small-lucky-shop Imaczi 2039-Sofa Set Blue Sofas","Comfort\n" +
                    "sofa with ottoman\n" +
                    "square arms\n" +
                    "high-density foam padding\n" +
                    "sturdy Y-shaped coordinating silver chrome metal legs",9,1000.99,home,List.of("https://m.media-amazon.com/images/I/61ZulOm-p-L._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/51p5c9hK3FL._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/716XOTZf6PL._AC_SL1500_.jpg"));
            Product book = new Product("The Book Thief Paperback – September","The extraordinary #1 New York Times bestseller about the ability of books to feed the soul even in the darkest of times.\n" +
                    " \n" +
                    "Nominated as one of America's best-loved novels by PBS’s The Great American Read.",2888,6.99,books,List.of("https://m.media-amazon.com/images/P/0375842209.01._SCLZZZZZZZ_SX500_.jpg", "https://images-na.ssl-images-amazon.com/images/I/71QktZRlDrL.jpg"));
            Product clean = new Product("O-Cedar EasyWring Microfiber Spin Mop & Bucket Floor Cleaning System + 2 Extra Refills, Red/Gray","REMOVES OVER 99% OF BACTERIA W/ JUST WATER: O-Cedar's EasyWring Microfiber Spin Mop & Bucket Floor Cleaning System provides a deep-cleaning solution for all your at home needs by effectively removing dirt, grime and over 99% of bacteria with just water! It is safe on all hard floor surfaces, including finished hardwood, wood, laminate, tile, vinyl, and more.",22,54.79,cleaning,List.of("https://m.media-amazon.com/images/I/71aliOn9h8L._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/81Q+U8xEf5L._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/81-8e98oVzL._AC_SL1500_.jpg", "https://m.media-amazon.com/images/I/81F+MwHCdBL._AC_SL1500_.jpg"));
            Product autom = new Product("Graco Extend2Fit Convertible Car Seat, Ride Rear Facing Longer with Extend2Fit, Gotham","Convertible car seat grows with your child from rear-facing harness ( 4-50lbs) to forward-facing harness (22-65 pounds)",22,190.71,automotive,List.of("https://m.media-amazon.com/images/I/71wNWtNb0sL._SL1500_.jpg", "https://m.media-amazon.com/images/I/816s9aGRJYL._SL1500_.jpg", "https://m.media-amazon.com/images/I/81yKeENMHXL._SL1500_.jpg"));

            productRepository.saveAll(List.of(tv,iphone,camera,macbook,cloth,sofa,book,clean, autom));
        };
    }
}
