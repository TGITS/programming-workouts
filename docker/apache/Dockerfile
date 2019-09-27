# Define the base image on top of which we are going to customize
FROM ubuntu:latest

# Run the command to install apache as a prerequisite
RUN apt-get update && apt-get install -y apache2

# Run the command to Create a DocumentRoot
RUN mkdir -p /var/www/html/pw

# Copy the file from the Current Host Directory to the Container
ADD default-website.conf /etc/apache2/sites-available/
ADD index.html /var/www/html/pw

# Run one more command to change the ownership of the file we copied
RUN chown root:root /etc/apache2/sites-available/default-website.conf

# Enable this website using a2ensite - command
RUN a2ensite default-website

# Say that our application is exposing a Port
EXPOSE 80

# the Default command and the Command to start the app in our container
CMD ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"]