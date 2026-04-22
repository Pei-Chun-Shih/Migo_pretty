from flask import Flask, request, jsonify

app = Flask(__name__)

accounts = []

@app.route('/accounts', methods=['POST'])
def create_account():
    data = request.get_json()
    account = {
        "id": len(accounts) + 1,
        "name": data.get("name"),
        "email": data.get("email")
    }
    accounts.append(account)
    return jsonify(account), 201

if __name__ == '__main__':
    app.run(debug=True)
