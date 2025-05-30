from flask import Flask, request, render_template
import requests

app = Flask(__name__)

# Replace 'your_subscription_key_here' with your actual Azure subscription key
SUBSCRIPTION_KEY = 'Your Subscription Key'
REGION = 'global'  # Replace 'your_region_here' with your Azure region, e.g., 'westeurope'

# Microsoft Translator API function
def translate_text_microsoft(text, target_language, subscription_key, region, source_language=''):
    endpoint = "https://api.cognitive.microsofttranslator.com/translate"
    params = {
        'api-version': '3.0',
        'to': target_language,
        'from': source_language
    }
    headers = {
        'Ocp-Apim-Subscription-Key': subscription_key,
        'Content-type': 'application/json',
        'Ocp-Apim-Subscription-Region': region
    }
    body = [{
        'text': text
    }]
    response = requests.post(endpoint, params=params, headers=headers, json=body)
    if response.status_code == 200:
        translated_text = response.json()[0]['translations'][0]['text']
        return translated_text
    else:
        return f"Error: {response.status_code}, {response.text}"

@app.route('/', methods=['GET', 'POST'])
def index():
    translated_text = ''
    if request.method == 'POST':
        text = request.form['text']
        target_language = request.form['target_language']
        source_language = request.form.get('source_language', '')
        translated_text = translate_text_microsoft(text, target_language, SUBSCRIPTION_KEY, REGION, source_language)
    return render_template('index.html', translated_text=translated_text)

if __name__ == '__main__':
    app.run(debug=True)
