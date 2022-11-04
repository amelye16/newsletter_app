Readme File

Newsletter Stori Challenge

The purpose of this document is to have the clear instructions to run the project


Warning:

It is needed to wait around 10 to 15 seconds once the docker-compose starts because "mysql" image takes about 15 seconds to be ready. However, backend needs it ready before run, in the mean time it will be launch some exceptions until "mysql" image is done


Step 1.

Clone the repository in your computer using: git clone command
$ git clone https://github.com/amelye16/newsletter_app.git


Step 2.

Once you have downloaded the repository, check the nexts steps:


A. Run the project with local images

1) Create the images

Navigate into project folder:

$ cd <project-folder_name> (stori_challenge)

1.1) Create mysql image:

$ cd mysql
$ docker build -t newsletter_mysql .

1.2) Create backend image:

Go back to project folder:
$ cd ..

$ cd backend/newsletter_app
$ docker build -t newsletter_backend .

1.3) Create frontend image:

Go back to project folder:
$ cd ../..

$ cd frontend/newsletter_app
docker build -t newsletter_frontend .

2) Once images are created navigate to project folder:

2.1) Go back to project folder:
$ cd ../..

2.2) Run docker-compose:

You need to have available these ports:
8080: backend
3306: mysql
3000: fronted

$ docker-compose up (to see the logs)
$ docker-compose up -d (run in daemon way, without logs)

Step 3. Once you have downloaded the repository, check the nexts steps:

3) Once the compose is up and ready, go to: http://localhost:3000/
3.1) Home: 
	You can see the instructions for the challenge.
3.2) Create Newsletter:
	You can create a newsletter, fill the form with the data required (title, description, newsletter type, file), you can use the existing files in the project for testing (newsletter_files folder).
	If you want to send the newsletter at the moment, press the “Send Newsletter” button at the bottom, fill the form with the data required, you can send the email to one user only or a list of users:
	One email: It is necessary name and email
	List of email: .csv file, including name and email, you can use the existing .csv file in the project for testing and to check the format (csv_file folder).
3.3) Display Newsletter List:
	You can see the list of newsletters created. If the newsletters are in status "wait" you can send it
	If you want to send the newsletter, press the blue little buttom in each row, fill the form with the data required, you can send the email to one user only or a list of users:
	One email: It is necessary name and email
	List of email: .csv file, including name and email, you can use the existing .csv file in the project for testing and to check the format (csv_file folder).
3.4 Create Newsletter Type:
	You can create a newsletter type. Fill the form with the data required (name, description), you can use that newsletter type created for create another newsletter
3.5 Display Statistics: 
	You can see a list for the statistics from the database
3.6 Unsuscribed: Available from email
	If you received an email, you can unsubscribe to the newsletter, the option is at the bottom of the email, you need to check the checkbox in the list and send it

4) To stop the service:
    Use ctrl+c if you run the compose with logs.
    Use next command if you're running with -d option:
    docker-compose-img-repository>docker-compose down --rmi all


To validate database info.

You can connect to the dabase (when the docker-compose is up and running) using the mysql client of your preference and next credentials:

    hostname: localhost
    port: 3306
    username:root
    password:root