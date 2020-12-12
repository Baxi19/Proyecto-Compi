using System;
using System.Collections;

namespace moduloPila
{
    public class Pila{
        Stack pila = new Stack ();
        public void push (dynamic obj){
            pila.Push(obj);
        }
        public dynamic pop()
        {
            return pila.Pop ();
        }
        
        public Boolean exchange()
        {
            try
            {
                dynamic value1 = pila.Pop ();
                dynamic value2 = pila.Pop ();
                pila.Push(value1);
                pila.Push(value2);
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
            
        }

        public void imprimir () {
            foreach ( Object obj in pila) {
                Console.Write( "    {0}", obj );
            }
       }
            
    }
}
