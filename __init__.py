from flask import Flask
from flask_talisman import Talisman

def create_app():
    app = Flask(__name__)

    # Apply Talisman security headers
    Talisman(app)

    @app.route("/")
    def home():
        return "Hello, Secure App!"

    return app
