from flask import Flask
from flask import Flask, flash, redirect, render_template, request, session, abort
from flask_httpauth import HTTPBasicAuth

import os
import sys

sys.path.append(sys.path[0])
from lib.database_connection import *

app = Flask(__name__)

@app.route('/users/<user>')
def hello_user(user):
    """
    this serves as a demo purpose
    :param user:
    :return: str
    """
    return "Hello %s!" % user

if __name__ == '__main__':
    app.secret_key = os.urandom(12)
    app.run()
