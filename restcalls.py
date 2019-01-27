import requests
import pandas as pd
import os
import glob
path = r"C:\Users\absaleem\Desktop\neueda"                     # use your path
all_files = glob.glob(os.path.join(path, "*.csv"))     # advisable to use os.path.join as this makes concatenation OS independent

df_from_each_file = (pd.read_csv(f) for f in all_files)
concatenated_df = pd.concat(df_from_each_file, ignore_index=True)

#df = pd.read_csv("C:/Users/absaleem/Desktop/MOCK_DATA.csv");
print(concatenated_df)
for index, row in concatenated_df.iterrows():
    print(row[0])
    print(row[1])
    r = requests.post("http://localhost:8080/greeting", json={'url': row[1], 'ipAddr': row[0]})
    print(r)




