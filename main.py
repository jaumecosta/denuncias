from flask import Flask, request, make_response, redirect, render_template, session, url_for
from pymongo import MongoClient


app = Flask(__name__)
app.secret_key = 'todoSuperSecreto'


MONGO_URL_ATLAS = 'mongodb+srv://jaumecosta:rata@cluster0-bmlh7.mongodb.net/test'

client = MongoClient(MONGO_URL_ATLAS, ssl_cert_reqs=False)
db = client['denuncia_personas']
collection = db['denuncia']


@app.route('/')
def index():
    return redirect('/home')


@app.route('/home', methods=['GET', 'POST'])
def home():
    if request.method == 'POST':
        global dni
        dni = request.form.get('dni')
        collection.insert_one({'dni': request.form.get('dni')})
        return redirect(url_for('resultado'))
    return render_template('home.html')


@app.route('/resultado', methods=['GET', 'POST'])
def resultado():
    if request.method == 'POST':
        dni = request.form.get('dni')
        localizacion = request.form.get('localizacion')
        ficha_local = collection.insert_one(
            {'dni': dni, 'localizacion': localizacion}
        )
        lista_total = collection.find(
            {'dni': dni, 'localizacion': localizacion}

        )
        lista_total = list(lista_total)
        limpio = []
        for i in range(len(lista_total)):
            limpio += list(lista_total[i].values())
        return render_template('resultado.html', lista_total=limpio)


if __name__ == "__main__":
    app.run('0.0.0.0', '5000', debug=True)
