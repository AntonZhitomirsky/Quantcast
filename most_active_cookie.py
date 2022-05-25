import argparse
import subprocess

parser=argparse.ArgumentParser(description='Script returning the most active cookie in a day')

parser.add_argument('cookie_file', help='file containing source of the cookie information')
parser.add_argument('-d', required=True, default="default_input", type=str, nargs=1, help='flag which preceedes the date of the search space in yyyy-mm-dd format')

options = parser.parse_args()
args = vars(options)

cookie_file = args["cookie_file"]
date = args["d"]

print(cookie_file)
print(date)

subprocess.run("javac src\main\java\Main.java", shell=True)
output = subprocess.check_output("java src\main\java\Main", stderr=subprocess.PIPE)
print(output)