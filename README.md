#### Key Components: Java 17, Spring (Boot, MVC, Data JPA), Postgres, HTML, Thymeleaf, Bootstrap, Maven, Lombok, Hibernate



# Doctor's Guide

This documentation provides instructions on how to run, deploy, and use the "Doctor's Guide" application. The application is written in Java and built on the Spring Framework using Spring MVC. Maven is used for project build. The database is implemented using Postgres. The graphical interface is presented in HTML using Thymeleaf and Bootstrap.

## Running and Deployment

1. **Database:**
      - Start the Postgres server.
      - Use the sql script `doctorsquide_postgres.sql`, located in the root of the project, to create the database and its minimal data.

2. **Application:**
      - Ensure Java version 17 and Maven are installed.
      - Run the application using Maven command:
      ```bash
         mvn spring-boot:run
      ```
      - The application will be accessible at http://localhost:8080.
      
## Using the Application

### 1. Filling out the patient examination form
   - Navigate to the /patient_examination_form page in your web browser (or navigate from the homepage).
   - Fill out the form with patient information.
   - Click the "Save" button.
### 2. Checking and adjusting medication stocks
   - Go to the /storage page to check medication stocks (or navigate from the homepage).
   - To add a new medication, go to the /add_medicine page and enter the necessary information.
   - To change the quantity of medication or delete it, go to the /storage page, click 'Edit' in the corresponding row to edit the quantity, and 'Delete' to remove the medication from the database.
### 3. Disease guide
   - Navigate to the /diseases page to search for and view information about diseases and corresponding medications (or navigate from the homepage).

## Working with an empty database

If the database is empty, you can follow these steps to create the necessary records:

1. **Building the medication database:**
   - Go to the `/add_medicine` page and add information about a new medication.
   - Save the information.

2. **Building the patient database:**
   - Go to the `/patient_examination_form` page and fill out the form for a new patient.
   - Save the information.

Note that the disease, symptom, and diagnostic procedure database should be built first as it is necessary for storing patient examination data.

Additional features and configurations can be found in the respective sections of the code and configuration files.

***

# Довідник лікаря

Ця документація надає інструкції по запуску, розгортанню та використанню "Довідника лікаря" (Doctor's Guide). 
Застосування написане мовою Java та побудоване на базі Spring Framework з використанням Spring MVC. 
Для збирання проєкту використовується Maven. База даних реалізована з використанням Postgres. 
Графічний інтерфейс представлений HTML з використанням Thymeleaf та Bootstrap.

## Запуск та розгортання

1. **База даних:**
    - Запустіть Postgres сервер.
    - Використовуйте sql-скрипт `doctorsquide_postgres.sql`, що знаходиться в корені проєкту, для створення бази даних та її мінімального наповнення.

2. **Застосування:**
    - Впевніться, що встановлено Java версії 17 та Maven.
    - Запустіть застосування за допомогою команди Maven:
      ```bash
      mvn spring-boot:run
      ```
    - Застосування буде доступне за адресою http://localhost:8080.

## Використання застосування

### 1. Заповнення звіту після огляду пацієнта
- Перейдіть на сторінку `/patient_examination_form` у вашому веб-браузері (або перейдіть з головної сторінки).
- Заповніть форму інформацією про пацієнта.
- Натисніть кнопку "Зберегти".

### 2. Перевірка запасів ліків та їх коригування
- Перейдіть на сторінку `/storage` для перевірки запасів ліків (або перейдіть з головної сторінки).
- Для додавання нового препарату перейдіть на сторінку `/add_medicine` та введіть необхідну інформацію.
- Для зміни кількості ліків або їх видалення перейдіть на сторінку `/storage`, в рядку відповідного препарату 
натисніть на 'Edit' для редагування кількості та 'Delete' для видалення препарату з бази.

### 3. Довідник хвороб
- Перейдіть на сторінку `/diseases` для пошуку та перегляду інформації про хвороби та відповідні 
для лікування ліки (або перейдіть з головної сторінки).

## Робота з порожньою базою даних

Якщо база даних порожня, можна скористатися наступними кроками для створення необхідних записів:

1. **Формування бази ліків:**
    - Перейдіть на сторінку `/add_medicine` та додайте інформацію про новий препарат.
    - Збережіть інформацію.

2. **Формування бази пацієнтів:**
    - Перейдіть на сторінку `/patient_examination_form` та заповніть форму для нового пацієнта.
    - Збережіть інформацію.

Зауважте, що базу хвороб, симптомів та діагностичних процедур слід формувати спочатку, оскільки вона є необхідною для зберігання даних про огляди пацієнтів.

Додаткові можливості та налаштування можна знайти у відповідних розділах коду та конфігураційних файлів.