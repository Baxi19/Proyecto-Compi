using System;
using System.Collections.Generic;
using System.Collections;
using AlmacenNameSpace;
using moduloPila;
//using static System.Linq.Enumerable;

namespace InstructionsNameSpace{
    class InstructionSet{   
        private List<KeyValuePair<string, dynamic>> instSet {get; set;}
        private Almacen almacenGlobal {get; set;} //se define un almacen global para manejo de variables globales y referencias a métodos
        private List<Almacen> almacenLocal {get; set;} //se define un almacén local para variables locales *** PUEDE QUE SE REQUIERA UNO POR CADA CONTEXTO PERO ESO DEBE DEFINIRSE ***
        private Pila pilaExprs;
        public Pila PilaExprs
        {
            get { return pilaExprs;}
            set { }
        }
        private int actualInstrIndex {get; set;}
        private List<dynamic> actualParamList = new List<dynamic>();
        
        public InstructionSet(){
            instSet = new List<KeyValuePair<string,dynamic>>() ;
            almacenGlobal = new Almacen("Global");
            almacenGlobal.setValue("write",-1);//por defecto se agrega el método write al almacen global
            almacenLocal = new List<Almacen>();
            pilaExprs = new Pila();
            actualInstrIndex=0;
        }

        public void addInst(string inst, dynamic param){
            instSet.Add(new KeyValuePair<string, dynamic>(inst,param));
        }

        private void runPUSH_LOCAL_I(string name){ //podría recibir el almacen del contexto en caso de que se requiera
            if(actualParamList.Count!=0){ 
                almacenLocal[almacenLocal.Count-1].setValue(name,actualParamList[0]); //el valor por defecto es el parámetro actual de turno
                actualParamList.RemoveAt(0); //se elimina el parámetro actual de turno
            }
            else
                //declara el elemento "name" en el almacen LOCAL con valor por defecto 0
                almacenLocal[almacenLocal.Count-1].setValue(name,0);
        }
        private void runPUSH_LOCAL_C(string name){ //podría recibir el almacen del contexto en caso de que se requiera
            if(actualParamList.Count!=0){ 
                almacenLocal[almacenLocal.Count-1].setValue(name,actualParamList[0]); //el valor por defecto es el parámetro actual de turno
                actualParamList.RemoveAt(0); //se elimina el parámetro actual de turno
            }
            else
                //declara el elemento "name" en el almacen LOCAL con valor por defecto ''
                almacenLocal[almacenLocal.Count-1].setValue(name,' ');
        }
        private void runPUSH_GLOBAL_I(string name){
            //declara el elemento "name" en el almacen GLOBAL con valor por defecto 0
            almacenGlobal.setValue(name,0);
        }
        private void runPUSH_GLOBAL_C(string name){
            //declara el elemento "name" en el almacen GLOBAL con valor por defecto ''
            almacenLocal[almacenLocal.Count-1].setValue(name,' ');
        }
        private void runDEF(string name){
            almacenGlobal.setValue(name,actualInstrIndex); //+1 porque la primer instrucción del método es el siguiente index
        }
        private void runLOAD_CONST(dynamic constant){
            //carga en la pila el valor entero contenido en "constant"
            pilaExprs.push(constant);
        }
        private void runLOAD_FAST(string varname){ //podría recibir el almacen del contexto en caso de que se requiera
            //busca en el almacén LOCAL el valor asociado a "varname" y lo inserta en la pila
            dynamic val;
            val = almacenLocal[almacenLocal.Count-1].getValue(varname); //EL GET VALUE DEBE DEVOLVER UN VALOR PARA PODERLO CARGAR A LA PILA
            pilaExprs.push(val);
        }
        private void runSTORE_FAST(string varname){ //podría recibir el almacen del contexto en caso de que se requiera
            //almacena el contenido del tope de la pila en el almacén LOCAL para la variable "varname"
            dynamic tope=0;
            tope = pilaExprs.pop(); //debe sacar el elemento de la pila y devolver su valor
            almacenLocal[almacenLocal.Count-1].updateValue(varname,tope);
        }
        private void runSTORE_GLOBAL(string varname){
            //almacena el contenido del tope de la pila en el almacén GLOBAL para la variable "varname"
            dynamic tope=0;
            tope = pilaExprs.pop(); //debe sacar el elemento de la pila y devolver su valor
            almacenGlobal.updateValue(varname,tope);
        }
        private void runLOAD_GLOBAL(string varname){
            //busca en el almacén GLOBAL el valor asociado a "varname" y lo inserta en la pila
            dynamic val;
            val = almacenGlobal.getValue(varname); //EL GET VALUE DEBE DEVOLVER UN VALOR PARA PODERLO CARGAR A LA PILA
            pilaExprs.push(val);
        }
        private void runCALL_FUNCTION(int numparams){
            int actualRef = pilaExprs.pop();  //el primer elemento de la pila trae la referencia del método a llamar --REVISAR SI FALTA SUMAR O NO A LA REFERENCIA
            if (actualRef == -1){ // es porque es el método print
                Console.WriteLine(pilaExprs.pop());
            }
            else{ 
                int latestInstr = actualInstrIndex;
                actualInstrIndex=actualRef;
                for (var i=1; i<= numparams; i++){ //carga en una lista, todos los elementos de la pila referentes a parámetros
                    actualParamList.Add(pilaExprs.pop());
                }
                actualParamList.Reverse(); //queda al revés toda la lista, se le da Vuelta!!!
                almacenLocal.Add(new Almacen("Local")); // se crea el almacen local para el método a llamar
                pilaExprs.push(latestInstr); //se carga en la pila la dirección de la referencia a la dirección por la que iba antes de cambiarla por la del método a llamar
                //en buena teoría con solo cambiar el índice de la instrucción actual y respaldar el anterior en la pila, ya el ciclo de afuera llama emulando un salto
            }
        }
        private void runRETURN_VALUE(){
            dynamic returnValue;
            returnValue = pilaExprs.pop(); // el tope de la pila tiene el elemento a retornar
            actualInstrIndex = pilaExprs.pop(); //si no hay problem,a, el tope de la pila tiene ahora la dirección a la que de sebe "saltar" que estaba respaldada
            pilaExprs.push(returnValue); //se vuelve a meter en la pila el retorno para lo que corresponda posteriormente
            
            //almacenLocal[almacenLocal.Count-1].printContainer();
            
            almacenLocal.RemoveAt(almacenLocal.Count-1); // se elimina el almacenLocaL del método

        }
        private void runRETURN(){
            actualInstrIndex = pilaExprs.pop(); //si no hay problema, el tope de la pila tiene ahora la dirección a la que de sebe "saltar" que estaba respaldada
            
            //almacenLocal[almacenLocal.Count-1].printContainer();
            
            almacenLocal.RemoveAt(almacenLocal.Count-1); // se elimina el almacenLocaL del método
            //SI EL RETURN NO SE HACE AL FINAL, ESTO DA PROBLEMAS PORQUE PUEDE HABER COSAS EN LA PILA
        }
        private void runEND(){
            //acaba la corrida y limpia/elimina las estructuras según sea el caso
        }
        private void runCOMPARE_OP(string op)
        {
            
            dynamic opn1= pilaExprs.pop();
            dynamic opn2= pilaExprs.pop();

            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores de los operandos son del mismo tipo, si no, se cae feo pero así debe ser... no hay mensajes de error
            //opn2= pilaExprs.pop();
            //opn1= pilaExprs.pop();
            try
            {
                if (op.Equals("=="))
                {
                    if (opn1 == opn2)
                    {
                        pilaExprs.push(true);
                    }
                    else
                    {
                        pilaExprs.push(false);   
                    }
                }
                else if (op.Equals("!="))
                {
                    if (opn1 != opn2)
                    {
                        pilaExprs.push(true);
                    }
                    else
                    {
                        pilaExprs.push(false);   
                    }
                }
                else if (op.Equals("<"))
                {
                    if (opn1 < opn2)
                    {
                        pilaExprs.push(true);
                    }
                    else
                    {
                        pilaExprs.push(false);   
                    }
                }
                else if (op.Equals("<="))
                {
                    if (opn1 <= opn2)
                    {
                        pilaExprs.push(true);
                    }
                    else
                    {
                        pilaExprs.push(false);   
                    }
                }
                else if (op.Equals(">"))
                {
                    if (opn1 > opn2)
                    {
                        pilaExprs.push(true);
                    }
                    else
                    {
                        pilaExprs.push(false);   
                    }  
                }
                else if (op.Equals(">="))
                {
                    if (opn1 >= opn2)
                    {
                        pilaExprs.push(true);
                    }
                    else
                    {
                        pilaExprs.push(false);   
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Can't Comparate, Error: " + e.Message);
            }
                
        }
        private void runBINARY_SUBSTRACT(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1-opn2);
        }
        private void runBINARY_ADD(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1+opn2);
        }
        private void runBINARY_MULTIPLY(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1*opn2);
        }
        private void runBINARY_DIVIDE(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1/opn2);
        }
        private void runBINARY_AND(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1&&opn2);
        }
        private void runBINARY_OR(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1&&opn2);
        }
        private void runBINARY_MODULO(){
            //obtiene dos operandos de la pila, opera según el operador y finalmente inserta el resultados de la operación en la pila
            //se asume que los valores son enteros, si no, se cae feo pero así debe ser... no hay mensajes de error
            dynamic opn2= pilaExprs.pop();
            dynamic opn1= pilaExprs.pop();
            pilaExprs.push(opn1%opn2);
        }
        private void runJUMP_ABSOLUTE(int target){
            //cambia el indice de la línea actual en ejecución a la indicada por "target"
            actualInstrIndex=target-1; //hay que restarle 1 por el incremento del ciclo posterior
        }
        
        private void runJUMP_IF_TRUE(int target){
            dynamic expr = pilaExprs.pop();
            if (expr.GetType() == typeof(bool))
            {
                if (expr == true )
                {
                    actualInstrIndex=target-1; //hay que restarle 1 por el incremento del ciclo posterior
                }
            }else if (expr.GetType() == typeof(int))
            {
                if (expr != 0 )
                {
                    actualInstrIndex=target-1; //hay que restarle 1 por el incremento del ciclo posterior
                }
            }

            //if(pilaExprs.pop()==true)
            //    actualInstrIndex=target-1; //hay que restarle 1 por el incremento del ciclo posterior
        }
        private void runJUMP_IF_FALSE(int target){
            
            //cambia el indice de la línea actual en ejecución a la indicada por "target" en caso de que el tope de la pila sea TRUE
            dynamic expr = pilaExprs.pop();
            if (expr.GetType() == typeof(bool))
            {
                if (expr == true)
                    actualInstrIndex = target - 1; //hay que restarle 1 por el incremento del ciclo posterior
            }
            else if (expr.GetType() == typeof(int))
            {
                if (expr != 0)
                    actualInstrIndex = target - 1; //hay que restarle 1 por el incremento del ciclo posterior
            }
            //cambia el indice de la línea actual en ejecución a la indicada por "target" en caso de que el tope de la pila sea FALSE
            //if(pilaExprs.pop()==false)
             //   actualInstrIndex=target-1; //hay que restarle 1 por el incremento del ciclo posterior
        }
        
         
        // Array
        private void runBUILD_LIST(int size){
            try
            {
                //se declara la lista
                List<dynamic> list = new List<dynamic>();
                // se llena la lista
                for (int i = 0; i < size; i++)
                {
                    list.Add(pilaExprs.pop());
                }
                pilaExprs.push(list);
            }
            catch (Exception e)
            {
                Console.WriteLine("Error al crear la lista: "+e.Message);
                throw;
            }

        }
        
        //LEN (list)
        private void runCALL_LEN(){
            try
            {
                //se obtiene la lista
                List<dynamic> list = pilaExprs.pop();
                pilaExprs.push(list.Count);
            }
            catch (Exception e)
            {
                Console.WriteLine("Error metodo LEN(): "+e.Message);
                throw;
            }
        }
        

        //método principal para correr todas las instrucciones de la lista... Este método debe recorrer la lista solo para agregar en el almacen global 
        //las variables y métodos que hayan y cuando se encuentre el Main, este método si debe ejecutarse línea por línea porque es el punto de inicio
        //del programa
        public void run(){
            while (actualInstrIndex <= instSet.Count-1){
                if (instSet[actualInstrIndex].Key.Equals("PUSH_GLOBAL_I")||
                instSet[actualInstrIndex].Key.Equals("PUSH_GLOBAL_C")||
                instSet[actualInstrIndex].Key.Equals("DEF")){
                    switch(instSet[actualInstrIndex].Key){
                        case "PUSH_GLOBAL_I": 
                            almacenGlobal.setValue(instSet[actualInstrIndex].Value,0);                            
                            break;
                        case "PUSH_GLOBAL_C": 
                            almacenGlobal.setValue(instSet[actualInstrIndex].Value,' ');                            
                            break;
                        case "DEF": 
                            if (instSet[actualInstrIndex].Value.Equals("Main")){
                                actualInstrIndex++; //se incrementa para que contenga la primera línea de código del Main
                                runMain();
                                return; 
                            }
                            else
                                runDEF(instSet[actualInstrIndex].Value);
                            break;
                    }
                    //Console.WriteLine("["+instSet[actualInstrIndex].Key + " " + instSet[actualInstrIndex].Value+"]");
                }
                actualInstrIndex++;
            }
        }

        private void runMain(){
            while (actualInstrIndex <= instSet.Count-1)
            {
                switch(instSet[actualInstrIndex].Key){
                        case "PUSH_LOCAL_I":
                            runPUSH_LOCAL_I(instSet[actualInstrIndex].Value);
                            break;
                        case "PUSH_LOCAL_C":
                            runPUSH_LOCAL_C(instSet[actualInstrIndex].Value);
                            break;
                        case "PUSH_GLOBAL_I":
                            runPUSH_GLOBAL_I(instSet[actualInstrIndex].Value);
                            break;
                        case "PUSH_GLOBAL_C":
                            runPUSH_GLOBAL_C(instSet[actualInstrIndex].Value);
                            break;
                        case "LOAD_CONST":
                            runLOAD_CONST(instSet[actualInstrIndex].Value);
                            break;
                        case "LOAD_FAST":
                            runLOAD_FAST(instSet[actualInstrIndex].Value);
                            break;
                        case "STORE_FAST":
                            runSTORE_FAST(instSet[actualInstrIndex].Value);
                            break;
                        case "STORE_GLOBAL":
                            runSTORE_GLOBAL(instSet[actualInstrIndex].Value);
                            break;
                        case "LOAD_GLOBAL":
                            if (instSet[actualInstrIndex].Value.Equals("len"))
                            {
                                runCALL_LEN();
                            }
                            else
                            {
                                runLOAD_GLOBAL(instSet[actualInstrIndex].Value);   
                            }
                            break;
                        case "CALL_FUNCTION":
                            runCALL_FUNCTION(instSet[actualInstrIndex].Value);
                            break;
                        case "RETURN_VALUE":
                            runRETURN_VALUE();
                            break;
                        case "RETURN":
                            runRETURN();
                            break;
                        case "END":
                            runEND();
                            break;
                        case "COMPARE_OP":
                            runCOMPARE_OP(instSet[actualInstrIndex].Value);
                            break;
                        case "BINARY_SUBSTRACT":
                            runBINARY_SUBSTRACT();
                            break;
                        case "BINARY_ADD":
                            runBINARY_ADD();
                            break;
                        case "BINARY_MULTIPLY":
                            runBINARY_MULTIPLY();
                            break;
                        case "BINARY_DIVIDE":
                            runBINARY_DIVIDE();
                            break;
                        case "BINARY_AND":
                            runBINARY_AND();
                            break;
                        case "BINARY_OR":
                            runBINARY_OR();
                            break;
                        case "BINARY_MODULO":
                            runBINARY_MODULO();
                            break;
                        case "JUMP_ABSOLUTE":
                            runJUMP_ABSOLUTE(instSet[actualInstrIndex].Value);
                            break;
                        case "JUMP_IF_TRUE":
                            runJUMP_IF_TRUE(instSet[actualInstrIndex].Value);
                            break;
                        case "JUMP_IF_FALSE":
                            runJUMP_IF_FALSE(instSet[actualInstrIndex].Value);
                            break;
                        case "BUILD_LIST":
                            runBUILD_LIST(instSet[actualInstrIndex].Value);
                            break;
                        //case "CALL_LEN":
                        //    runCALL_LEN();
                        //    break;
                        default:
                            throw new Exception("Instrucción no conocida");
                }
                //Console.WriteLine("["+instSet[actualInstrIndex].Key + " " + instSet[actualInstrIndex].Value+"]"); 
                actualInstrIndex++; 
            }
        }

        public void printInstructionSet(){
            Console.WriteLine();
            Console.WriteLine("Set de instrucciones: ");
            for(int i = 0; i < instSet.Count; i++){
                Console.WriteLine(i + " " + instSet[i].Key + " " + instSet[i].Value);
            }
        }
    }
}