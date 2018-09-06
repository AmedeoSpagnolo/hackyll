#!/usr/bin/python
import MySQLdb
import sys
import csv

sys.path.append(sys.path[0])
from .database_settings import *

connection = MySQLdb.connect (host = HOST,
                              user = USER,
                              passwd = PASSWD,
                              port = PORT,
                              db = DB)

def read_lines(file_name):
    with open(file_name, "r") as f:
        return f.read().splitlines()

def print_all_lines(data):
    for row in data:
        print ",".join([str(x) for x in row])

def export_csv(data, filename):
    with open(filename, 'w') as fp:
        myFile = csv.writer(fp)
        myFile.writerows(data)

def get(query):
    cursor = connection.cursor()
    cursor.execute(query)
    data = cursor.fetchall()
    cursor.close()
    return data

def post(query):
    cursor = connection.cursor()
    cursor.execute(query)
    cursor.close()
    connection.commit()

def delete(query):
    cursor = connection.cursor()
    cursor.execute(query)
    cursor.close()
    connection.commit()

def update(query):
    cursor = connection.cursor()
    cursor.execute(query)
    cursor.close()
    connection.commit()

def close():
    connection.close()

def isValidUser(email, passwd):
    query = 'SELECT * FROM users\
             WHERE email=\"%s\" \
             AND password=\"%s\";' % (email, passwd)
    data = get(query)
    if (len(data) == 0):
        return False
    else:
        return True
