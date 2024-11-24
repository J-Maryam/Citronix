
# 🌳🍋 **Citronix - Lemon Farm Management System** 🍋🌳

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

## 📖 **Project Overview**
**Citronix** is a management system designed for lemon farms, aimed at helping farmers track their production, harvest, and sales. The application is tailored to streamline the management of farms, fields, trees, harvests, and sales, with the goal of maximizing productivity based on the age of the lemon trees.

## 🚀 **Features**
### 🏡 **Farm Management**
- Create, update, and view information about farms (name, location, size, creation date).
- Multi-criteria search using `Criteria Builder`.

### 🌾 **Field Management**
- Associate fields with a farm with defined areas.
- Ensure consistency of areas: the sum of field areas in a farm must be strictly less than the total farm size.

### 🌲 **Tree Management**
- Track trees by their planting date, age, and the field they belong to.
- Calculate the age of trees.
- Determine annual productivity based on the age:
    - **Young Tree (< 3 years)**: 2.5 kg/season.
    - **Mature Tree (3-10 years)**: 12 kg/season.
    - **Old Tree (> 10 years)**: 20 kg/season.

### 🍂 **Harvest Management**
- Track seasonal harvests (winter, spring, summer, fall).
- Only one harvest per season (every 3 months).
- Record the date and total quantity harvested.

### 📊 **Harvest Details**
- Track the quantity harvested per tree for a given harvest.
- Associate each harvest detail with a specific tree.

### 💰 **Sales Management**
- Record sales with the date, unit price, client, and associated harvest.
- Revenue Calculation: `Revenue = quantity * unit price`.

## ⚙️ **Technical Requirements**
- **Spring Boot** for creating the REST API.
- Layered architecture: `Controller`, `Service`, `Repository`, `Entity`.
- Data validation using Spring annotations.
- Centralized exception handling.
- Unit testing with **JUnit** and **Mockito**.
- **Lombok** and Builder Pattern for simplified entity management.
- **MapStruct** for conversion between entities, DTOs, and View Models.

## 🎯 **Targeted Skills**
- Setting up the development environment.
- Developing business components.
- Contributing to project management.
- Defining the software architecture.
- Designing and managing relational databases.
- Developing data access components (SQL/NoSQL).
- Preparing and executing test plans.

## 📐 **Design**
### **UML Class Diagram**
A comprehensive class diagram is used to visualize the project's architecture, highlighting the relationships between controllers, services, repositories, and entities.

![UML Diagram]((https://github.com/user-attachments/assets/20058743-f4a8-4982-8f26-415d1e73129a)
)

## 🛠️ **Installation Guide**
To run the project locally, follow these steps:

### **Prerequisites**
- **Java 17** or higher
- **Maven**
- **PostgreSQL**
- **Git**

### **Setup**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/J-Maryam/Citronix.git
   ```
2. **Database Setup**:
    - Create a PostgreSQL database named `citronix`.
3. **Configuration**:
    - Update the `application.properties` file in `/src/main/resources/` with your database credentials.
4. **Build the Project**:
   ```bash
   mvn clean install
   ```
5. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

## 🧪 **Testing**
To run the unit tests:
```bash
mvn test
```

## 📦 **Deployment**
To build the JAR file for deployment:
```bash
mvn clean package
```
The executable JAR will be located in the `target` directory.

## 🗃️ **Data Model**
Here's a breakdown of the key entities:

- **Farm**: Represents a lemon farm with a unique name, location, and total area.
- **Field**: A field associated with a farm; must not exceed 50% of the farm's area.
- **Tree**: A lemon tree with attributes like planting date and age.
- **Harvest**: A record of lemon harvesting activities, categorized by season.
- **Harvest Details**: Detailed record of lemons harvested from each tree.
- **Sale**: A sale record linked to a particular harvest, with revenue calculation.

## 🌟 **Key Technologies & Libraries**
- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **JUnit** & **Mockito**
- **Lombok**
- **MapStruct**
- **Swagger** for API documentation.

## 🔄 **Project Management**
- **JIRA** is used for issue tracking and project management.
- **GitHub** repository for source code: [GitHub Repository](https://github.com/J-Maryam/Citronix.git).
- **Swagger** or **Postman Collection** available for API endpoint testing.

## 🗺️ **Roadmap**
1. 🌱 Initial setup of farm, field, and tree management.
2. 🌿 Implement harvest tracking and productivity calculations.
3. 🍃 Develop sales and revenue management features.
4. 🍀 Add additional constraints and validation.
5. 🌳 Refine UI and API for improved user experience.

## 🛡️ **Constraints**
- Minimum field size: **0.1 hectares** (1,000 m²).
- Maximum field size: **50% of farm size**.
- Max number of fields per farm: **10**.
- Tree density: **100 trees/hectare**.
- Maximum tree lifespan: **20 years**.
- Tree planting period: **March - May**.

## 👨‍💻 **Contributing**
1. Fork the project.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -am 'Add a new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Create a new Pull Request.

## 📜 **License**
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 📬 **Contact**
For questions or feedback, reach out to:

- 📧 Email: maryamjammaar1509@gmail.com
- 💼 LinkedIn: [click here](https://www.linkedin.com/in/maryam-jammar-78119823b/)
