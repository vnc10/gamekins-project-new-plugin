CREATE TABLE course (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        code VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE student (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         ra_number VARCHAR(20) UNIQUE NOT NULL,
                         course_id BIGINT,
                         FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE subject (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         code VARCHAR(20) UNIQUE NOT NULL,
                         course_id BIGINT,
                         FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE enrollment (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            status VARCHAR(50) DEFAULT 'Enrolled',
                            student_id BIGINT,
                            subject_id BIGINT,
                            FOREIGN KEY (student_id) REFERENCES student(id),
                            FOREIGN KEY (subject_id) REFERENCES subject(id),
                            UNIQUE (student_id, subject_id)
);