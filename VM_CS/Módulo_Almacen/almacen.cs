using System;
using System.Collections.Generic;

namespace AlmacenNameSpace
{
    public class Almacen{
        // PRELIMINARY DATA STRUCTURE:
        Dictionary<string, dynamic> data;

        // -----------------------------
        private String name;

        // constructor
        public Almacen(String name){
            this.data = new Dictionary<string, dynamic>();
            this.name = name;
        }

        // ADD VALUE TO THE DICTIONARY
        public void setValue(string key, dynamic value){
            if (searchValue(key))
            {
                updateValue(key, value);
            }
            else
            {
                this.data.Add(key, value);    
            }
        }

        // RETURN VALUE
        public dynamic getValue(string key){
            dynamic result = null;
            foreach (dynamic item in this.data){
                if (item.Key == key ){
                    //Console.WriteLine("Key: {0}, Value: {1}", item.Key, item.Value);
                    result = item.Value;
                }
            }
            return result;

        }

        // GET VALUE IF FOUND
        public bool searchValue(string key){
            if(this.data.ContainsKey(key)){
                //Console.WriteLine(key + " ITEM FOUND");
                return true;
            }
            return false;
            
        }

        // UPDATE VALUE IF FOUND
        public void updateValue(string key, dynamic newValue){
            bool found = searchValue(key);
            if(!found){
                Console.WriteLine("404 Item ( "+ key.ToString() +" ) not found!");
            }
            else{
                this.data[key] = newValue;
                //Console.WriteLine(key + " ITEM UPDATED!");
            }
        }

        // PRINT DICTIONARY
        // Almacen tiene u n item
        public void printContainer(){
            foreach (dynamic item in this.data){
                Console.WriteLine("Key: {0}, Value: {1} Type: {2}", item.Key, item.Value, item.Value.GetType());
            }
        }
        
    }
}