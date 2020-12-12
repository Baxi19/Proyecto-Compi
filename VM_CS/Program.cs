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
        public static void waitData()
        {
            byte[] data = new byte[1073741824]; 
            IPEndPoint endPoint = new IPEndPoint(IPAddress.Any, 8888);
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp); 
            socket.Bind(endPoint);
            Console.WriteLine("\nWaiting for a client...");
            EndPoint remote = (EndPoint) endPoint;
            int recived = socket.ReceiveFrom(data, ref remote);
            if (recived > 0)
            {
                string path = (System.IO.Directory.GetCurrentDirectory()+"\\desensamblador_codigo\\SocketData.txt");
                string text = Encoding.ASCII.GetString(data, 0, recived);
                using (FileStream fs = File.Create(path))
                {
                    byte[] info = new UTF8Encoding(true).GetBytes(text);
                    // Add some information to the file.
                    fs.Write(info, 0, info.Length);
                }
                InstructionSet instructionSet = new InstructionSet();
                Desensamblador desensamblador = new Desensamblador(ref instructionSet);
                desensamblador.desensamblar(path);
                instructionSet.run();
            }
            
        }

        public static void Main(string[] args)
        {
            waitData();
        }
    }
}
