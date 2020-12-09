using System;
using System.Linq;
using InstructionsNameSpace;
using System.IO;

namespace DesensambladorNameSpace {
    class Desensamblador{   
        public InstructionSet setInstrucciones;
        
        public Desensamblador(ref InstructionSet setInstrucciones){
            this.setInstrucciones = setInstrucciones;
        }


        public void desensamblar(string origen){
            FileInfo archivo = new FileInfo(origen);
            if (archivo.Exists){
                string line;
                System.IO.StreamReader file = new System.IO.StreamReader(origen);
                while ((line = file.ReadLine()) != null){
                    //Console.WriteLine(line);
                    string[] palabras = line.Split(' ');
                    //string instruccion = "Instrucción: ";
                    if (palabras.Length == 3){ //se podría prescindir del número inicial de la instrucción pero es para control
                        try{
                            int param = System.Convert.ToInt32(palabras[2]);//Primero se intenta convertir el parámetro a número
                            setInstrucciones.addInst(palabras[1], param);
                        }catch (FormatException){
                            char starterChar = palabras[2].ToCharArray()[0];
                            if (starterChar == '\'')
                                setInstrucciones.addInst(palabras[1], palabras[2].Replace("\'",""));//Si el parámetro no es un char
                            else if ((starterChar == '"'))
                                setInstrucciones.addInst(palabras[1], palabras[2].Replace("\"",""));//Si el parámetro no es un string
                            else
                                setInstrucciones.addInst(palabras[1], palabras[2]);//Si el parámetro no es un número válido para evitar error 
                        }
                    }else if (palabras.Length == 2){
                        setInstrucciones.addInst(palabras[1], null);//La instrucción no contiene parámetro.
                    }
                }
                file.Close();
            }
            else
                Console.WriteLine("File doesn´t exists!");
        }
        
    }
}
