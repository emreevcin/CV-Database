CREATE TABLE cvs (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_name TEXT UNIQUE NOT NULL,
	created_at TIMESTAMP DEFAULT NOW()
);
-- bu tabloda pdf attach etme gereksinimini yapabiliriz.

CREATE TABLE people (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,  -- Bunu kullanıcıdan değil, bir şekilde tıklayarak seçtiği cv'den background'da almalyız.
	image_url TEXT, -- CV sahibinin fotoğrafı için düşündüm, field değişebilir.
	first_name TEXT,
	last_name TEXT,
	title TEXT,
	email TEXT,
	phone TEXT,
	city TEXT,
	country TEXT,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE works (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	occupation TEXT,
	employer TEXT,
	city TEXT,
	country TEXT,
	starting_date DATE,
	ending_date DATE,
	ongoing BOOL,
	activities_responsibilities TEXT,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
); -- people ile foreign key ilişkisine gerek var mı düşünülecek.

CREATE TABLE educations (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	instution TEXT,
	department TEXT,
	gpa FLOAT(3, 2),
	starting_date DATE,
	ending_date DATE,
	ongoing BOOL,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE certificates (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	education_name TEXT,
	company TEXT,
	verified_date DATE,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE skills (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	mother_tongue TEXT,
	other_languages TEXT,
	soft_skills TEXT,
	hard_skills TEXT,
	hobbies_interests TEXT
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE projects (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	title TEXT,
	starting_date DATE,
	ending_date DATE,
	ongoing BOOL,
	description TEXT,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE recomendations (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	name TEXT,
	role TEXT,
	email TEXT, 
	phone TEXT,
	description TEXT,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE others (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cv_id INTEGER NOT NULL,
	header TEXT,
	title TEXT,
	description TEXT,
	created_at TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY(cv_id) REFERENCES cvs(id)
);

CREATE TABLE tags (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	tag_name TEXT UNIQUE,
	created_at TIMESTAMP DEFAULT NOW()
);