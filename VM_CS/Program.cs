using System;
using InstructionsNameSpace;
using AlmacenNameSpace;
using DesensambladorNameSpace;
using moduloPila;
using System.Collections;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Minics.exe
{
    class Program
    {
        public static Boolean getData(int PORT)
        {
            // Server
            byte[] data = new byte[1073741824]; // 1 gigabyte 
            IPEndPoint endPoint = new IPEndPoint(IPAddress.Any, PORT);
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp); 
            socket.Bind(endPoint);
            
            Console.WriteLine("\nSERVER> lISTENING...");
            EndPoint remote = endPoint;
            int recived = socket.ReceiveFrom(data, ref remote);
            Console.WriteLine("SERVER> DATA RECEIVED");
            if (recived > 0)
            {
                string path = (Path.GetDirectoryName(Path.GetDirectoryName(System.IO.Directory.GetCurrentDirectory()))+"\\desensamblador_codigo\\SocketData.txt");
                string text = Encoding.ASCII.GetString(data, 0, recived);
                using (FileStream fs = File.Create(path))
                {
                    byte[] info = new UTF8Encoding(true).GetBytes(text);
                    fs.Write(info, 0, info.Length);
                }
                InstructionSet instructionSet = new InstructionSet();
                Desensamblador desensamblador = new Desensamblador(ref instructionSet);
                desensamblador.desensamblar(path);
                instructionSet.run();
                
                string outPath = (Path.GetDirectoryName(Path.GetDirectoryName(System.IO.Directory.GetCurrentDirectory()))+"\\output.txt");
                using (FileStream fs = File.Create(outPath))
                {
                    byte[] info = new UTF8Encoding(true).GetBytes(instructionSet.output);
                    fs.Write(info, 0, info.Length);
                }
            }
            return true;
        }
        
        public static void Main(string[] args)
        {
            int PORT = 8888;
            try
            {
                Console.WriteLine("SERVER> STARTING ON PORT: "+ PORT);
                while (true)
                {
                    getData(PORT);    
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("SERVER> ERROR : " + e.Message);
                Console.WriteLine("Press any key to finish");
                Console.ReadLine();
                Environment.Exit(0);
            }
        }
    }
}
