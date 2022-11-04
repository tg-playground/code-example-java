FROM mysql/mysql-server:8.0.31
RUN mkdir -p /etc/mysql/conf.d
# moving my custom config file to folder /etc/my.cnf.d or folder /etc/mysql/conf.d are not working. I don't know why. The two folders may not include by the MySQL server automatically.
COPY my.cnf /etc/mysql/conf.d/my.cnf