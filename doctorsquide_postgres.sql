-- Create the 'symptom' table
CREATE TABLE symptom (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

-- Create the 'diagnostic_procedure' table
CREATE TABLE diagnostic_procedure (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL
);

-- Create the 'active_ingredient' table
CREATE TABLE active_ingredient (
                                   id SERIAL PRIMARY KEY,
                                   name VARCHAR(255) NOT NULL
);

-- Create the 'form' table
CREATE TABLE form (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL
);

-- Create the 'disease' table without symptom and diagnostic procedure references
CREATE TABLE disease (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) UNIQUE NOT NULL
);

-- Create the 'medicine' table without active ingredient reference
CREATE TABLE medicine (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          form_id INT,
                          FOREIGN KEY (form_id) REFERENCES form(id)
);

-- Creating table: medicine_disease
CREATE TABLE medicine_disease (
                                  medicine_id INT,
                                  disease_id INT,
                                  PRIMARY KEY (medicine_id, disease_id),
                                  FOREIGN KEY (medicine_id) REFERENCES medicine(id),
                                  FOREIGN KEY (disease_id) REFERENCES disease(id)
);

-- Create a many-to-many relationship table for diseases and symptoms
CREATE TABLE disease_symptom (
                                 disease_id INT,
                                 symptom_id INT,
                                 PRIMARY KEY (disease_id, symptom_id),
                                 FOREIGN KEY (disease_id) REFERENCES disease(id),
                                 FOREIGN KEY (symptom_id) REFERENCES symptom(id)
);

-- Create a many-to-many relationship table for diseases and diagnostic procedures
CREATE TABLE disease_diagnostic_procedure (
                                              disease_id INT,
                                              diagnostic_procedure_id INT,
                                              PRIMARY KEY (disease_id, diagnostic_procedure_id),
                                              FOREIGN KEY (disease_id) REFERENCES disease(id),
                                              FOREIGN KEY (diagnostic_procedure_id) REFERENCES diagnostic_procedure(id)
);



-- Create the 'medicine_active_ingredient' table for many-to-many relationship
CREATE TABLE medicine_active_ingredient (
                                            medicine_id INT,
                                            active_ingredient_id INT,
                                            PRIMARY KEY (medicine_id, active_ingredient_id),
                                            FOREIGN KEY (medicine_id) REFERENCES medicine(id),
                                            FOREIGN KEY (active_ingredient_id) REFERENCES active_ingredient(id)
);

-- Insert data into 'active_ingredient' table
INSERT INTO active_ingredient (name) VALUES
                                         ('Ibuprofen'),
                                         ('Cetirizine'),
                                         ('Omeprazole'),
                                         ('Amoxicillin'),
                                         ('Metformin'),
                                         ('Levothyroxine'),
                                         ('Sertraline'),
                                         ('Aspirin'),
                                         ('Paracetamol'),
                                         ('Caffeine');

-- Insert data into 'form' table
INSERT INTO form (name) VALUES
                            ('Tablet'),
                            ('Capsule'),
                            ('Syrup'),
                            ('Patch'),
                            ('Intravenous Injection'),
                            ('Intramuscular Injection');

-- Insert data into 'symptom' table
INSERT INTO symptom (name) VALUES
                               ('Fever'),
                               ('Cough'),
                               ('Headache'),
                               ('Fatigue'),
                               ('Inflammation'),
                               ('Allergic Reaction'),
                               ('Heartburn'),
                               ('Chills'),
                               ('High Blood Sugar'),
                               ('Low Energy');

-- Insert data into 'diagnostic_procedure' table
INSERT INTO diagnostic_procedure (name) VALUES
                                            ('Blood Test'),
                                            ('MRI Scan'),
                                            ('X-ray'),
                                            ('CT Scan'),
                                            ('Ultrasound'),
                                            ('Endoscopy'),
                                            ('Colonoscopy'),
                                            ('Echocardiogram'),
                                            ('Electrocardiogram'),
                                            ('Pulmonary Function Test');

-- Insert data into 'disease' table
INSERT INTO disease (name) VALUES
                               ('Pain and Inflammation'),
                               ('Allergies'),
                               ('Gastroesophageal Reflux Disease (GERD)'),
                               ('Bacterial Infections'),
                               ('Type 2 Diabetes'),
                               ('Hypothyroidism'),
                               ('Depression'),
                               ('Joint Pain'),
                               ('Severe Bacterial Infections'),
                               ('Type 1 Diabetes'),
                               ('Migraine');

-- Insert data into 'medicine' table with multiple forms
INSERT INTO medicine (name, form_id) VALUES
                                         ('Advil', 1),
                                         ('Zyrtec', 1),
                                         ('Prilosec', 2),
                                         ('Amoxil', 3),
                                         ('Glucophage', 1),
                                         ('Synthroid', 1),
                                         ('Zoloft', 1),
                                         ('Duragesic', 4),
                                         ('Augmentin', 5),
                                         ('Lantus', 6),
                                         ('Advil', 2), -- Advil can also be in Capsule form
                                         ('Zyrtec', 3), -- Zyrtec can also be in Syrup form
                                         ('Prilosec', 1), -- Prilosec can also be in Tablet form
                                         ('Amoxil', 1), -- Amoxil can also be in Tablet form
                                         ('Glucophage', 2), -- Glucophage can also be in Capsule form
                                         ('Synthroid', 2), -- Synthroid can also be in Capsule form
                                         ('Zoloft', 2), -- Zoloft can also be in Capsule form
                                         ('Duragesic', 5), -- Duragesic can also be in Intravenous Injection form
                                         ('Augmentin', 4), -- Augmentin can also be in Patch form
                                         ('Lantus', 4), -- Lantus can also be in Patch form
                                         ('Triagesic', 1),
                                         ('Triagesic', 2);

-- Insert data into 'medicine_active_ingredient' table
INSERT INTO medicine_active_ingredient (medicine_id, active_ingredient_id) VALUES
                                                                               (1, 1), -- Advil contains Ibuprofen
                                                                               (2, 2), -- Zyrtec contains Cetirizine
                                                                               (3, 3), -- Prilosec contains Omeprazole
                                                                               (4, 4), -- Amoxil contains Amoxicillin
                                                                               (5, 5), -- Glucophage contains Metformin
                                                                               (6, 6), -- Synthroid contains Levothyroxine
                                                                               (7, 7), -- Zoloft contains Sertraline
                                                                               (8, 1), -- Duragesic contains Ibuprofen
                                                                               (9, 4), -- Augmentin contains Amoxicillin
                                                                               (10, 5), -- Lantus contains Metformin
                                                                               (11, 1), -- Advil (Capsule) contains Ibuprofen
                                                                               (12, 2), -- Zyrtec (Syrup) contains Cetirizine
                                                                               (13, 3), -- Prilosec (Tablet) contains Omeprazole
                                                                               (14, 4), -- Amoxil (Tablet) contains Amoxicillin
                                                                               (15, 5), -- Glucophage (Capsule) contains Metformin
                                                                               (16, 6), -- Synthroid (Capsule) contains Levothyroxine
                                                                               (17, 7), -- Zoloft (Capsule) contains Sertraline
                                                                               (18, 8), -- Duragesic (Intravenous Injection) contains Ibuprofen
                                                                               (19, 9), -- Augmentin (Patch) contains Amoxicillin
                                                                               (20, 10), -- Lantus (Patch) contains Metformin
                                                                               (21, 8), -- Triagesic contains Aspirin
                                                                               (21, 9), -- Triagesic contains Paracetamol
                                                                               (21, 10), -- Triagesic contains Caffeine
                                                                               (22, 8), -- Triagesic contains Aspirin
                                                                               (22, 9), -- Triagesic contains Paracetamol
                                                                               (22, 10); -- Triagesic contains Caffeine

-- Inserting combined sample data into disease_symptom
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES
                                                         (1, 3), -- Pain and Inflammation, Headache
                                                         (2, 2), -- Allergies, Cough
                                                         (3, 3), -- GERD, Headache
                                                         (4, 1), -- Bacterial Infections, Fever
                                                         (5, 4), -- Type 2 Diabetes, Fatigue
                                                         (6, 4), -- Hypothyroidism, Fatigue
                                                         (7, 4), -- Depression, Fatigue
                                                         (8, 1), -- Pain Management, Fever
                                                         (9, 1), -- Bacterial Infections, Fever
                                                         (10, 4), -- Type 1 Diabetes, Fatigue
                                                         (2, 4), -- Allergies, Fatigue
                                                         (3, 1), -- GERD, Fever
                                                         (4, 3), -- Bacterial Infections, Headache
                                                         (5, 2), -- Type 2 Diabetes, Cough
                                                         (6, 1), -- Hypothyroidism, Fever
                                                         (8, 2), -- Pain Management, Cough
                                                         (9, 3), -- Bacterial Infections, Headache
                                                         (11, 1), -- Migraine, Fever
                                                         (1, 2), -- Pain and Inflammation, Cough
                                                         (2, 3), -- Allergies, Headache
                                                         (3, 4), -- GERD, Fatigue
                                                         (4, 2), -- Bacterial Infections, Cough
                                                         (5, 1), -- Type 2 Diabetes, Fever
                                                         (6, 3), -- Hypothyroidism, Headache
                                                         (7, 2), -- Depression, Cough
                                                         (8, 3), -- Pain Management, Headache
                                                         (9, 4), -- Bacterial Infections, Fatigue
                                                         (10, 1), -- Type 1 Diabetes, Fever
                                                         (11, 4), -- Migraine, Fatigue
                                                         (11, 3); -- Migraine, Headache

-- Insert data into 'disease_diagnostic_procedure' table
INSERT INTO disease_diagnostic_procedure (disease_id, diagnostic_procedure_id) VALUES
                                                                                   (1, 1), -- Pain and Inflammation, Blood Test
                                                                                   (2, 2), -- Allergies, MRI Scan
                                                                                   (3, 3), -- GERD, X-ray
                                                                                   (4, 1), -- Bacterial Infections, Blood Test
                                                                                   (5, 3), -- Type 2 Diabetes, X-ray
                                                                                   (6, 1), -- Hypothyroidism, Blood Test
                                                                                   (7, 1), -- Depression, Blood Test
                                                                                   (8, 2), -- Pain Management, MRI Scan
                                                                                   (9, 3), -- Bacterial Infections, X-ray
                                                                                   (10, 1), -- Type 2 Diabetes, Blood Test
                                                                                   (11, 1), -- Migraine, Blood Test
                                                                                   (1, 8), -- Pain and Inflammation, Echocardiogram
                                                                                   (2, 9), -- Allergies, Electrocardiogram
                                                                                   (3, 10), -- GERD, Pulmonary Function Test
                                                                                   (4, 4), -- Bacterial Infections, CT Scan
                                                                                   (5, 5), -- Type 2 Diabetes, Ultrasound
                                                                                   (10, 10); -- Type 2 Diabetes, Pulmonary Function Test


-- Insert data into 'disease_diagnostic_procedure' table
INSERT INTO medicine_disease (medicine_id, disease_id) VALUES
                                                           (1, 1), -- Advil, Pain and Inflammation
                                                           (2, 2), -- Zyrtec, Allergies
                                                           (3, 3), -- Prilosec, GERD
                                                           (4, 4), -- Amoxil, Bacterial Infections
                                                           (5, 5), -- Glucophage, Type 2 Diabetes
                                                           (6, 6), -- Synthroid, Hypothyroidism
                                                           (7, 7), -- Zoloft, Depression
                                                           (8, 8), -- Duragesic, Pain Management
                                                           (9, 9), -- Augmentin, Severe Bacterial Infections
                                                           (10, 5), -- Lantus, Type 2 Diabetes
                                                           (10, 10), -- Lantus, Type 2 Diabetes
                                                           (22, 11), -- Triagesic, Migraine
                                                           (21, 11), -- Triagesic, Migraine
                                                           (11, 1), -- Advil, Pain and Inflammation
                                                           (12, 2), -- Zyrtec, Allergies
                                                           (13, 3), -- Prilosec, GERD
                                                           (14, 4), -- Amoxil, Bacterial Infections
                                                           (15, 5), -- Glucophage, Type 2 Diabetes
                                                           (16, 6), -- Synthroid, Hypothyroidism
                                                           (17, 7), -- Zoloft, Depression
                                                           (18, 8), -- Duragesic, Pain Management
                                                           (19, 9), -- Augmentin, Severe Bacterial Infections
                                                           (20, 5); -- Lantus, Type 2 Diabetes

-- Add new column "quantity" to the "medicine" table
ALTER TABLE medicine
    ADD COLUMN quantity INTEGER;

-- Update the "quantity" column with integer values
UPDATE medicine
SET quantity = CASE
                   WHEN name = 'Advil' AND form_id = 1 THEN 100
                   WHEN name = 'Zyrtec' AND form_id = 1 THEN 150
                   WHEN name = 'Prilosec' AND form_id = 2 THEN 200
                   WHEN name = 'Amoxil' AND form_id = 3 THEN 50
                   WHEN name = 'Glucophage' AND form_id = 1 THEN 120
                   WHEN name = 'Synthroid' AND form_id = 1 THEN 80
                   WHEN name = 'Zoloft' AND form_id = 1 THEN 90
                   WHEN name = 'Duragesic' AND form_id = 4 THEN 30
                   WHEN name = 'Augmentin' AND form_id = 5 THEN 70
                   WHEN name = 'Lantus' AND form_id = 6 THEN 110
                   WHEN name = 'Advil' AND form_id = 2 THEN 60
                   WHEN name = 'Zyrtec' AND form_id = 3 THEN 180
                   WHEN name = 'Prilosec' AND form_id = 1 THEN 40
                   WHEN name = 'Amoxil' AND form_id = 1 THEN 25
                   WHEN name = 'Glucophage' AND form_id = 2 THEN 70
                   WHEN name = 'Synthroid' AND form_id = 2 THEN 100
                   WHEN name = 'Zoloft' AND form_id = 2 THEN 150
                   WHEN name = 'Duragesic' AND form_id = 5 THEN 45
                   WHEN name = 'Augmentin' AND form_id = 4 THEN 80
                   WHEN name = 'Lantus' AND form_id = 4 THEN 60
                   WHEN name = 'Triagesic' AND form_id = 1 THEN 55
                   WHEN name = 'Triagesic' AND form_id = 2 THEN 65
                   ELSE NULL
    END;
