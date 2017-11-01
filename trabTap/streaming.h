
class Streaming  //Singleton patter
{

	private:	
		Streaming();
		static Streaming instance=NULL;

	public:
		Streaming instanceStraming(string, string); //instancia uma
		string password; 	//Senha
		string userLogin;	//Login 
		int usetType; 		//Plano
		Diretorio directory; //Diretorio de musicas


};

class Diretorio //Composite Visito
{

};



class Playlist //Composite Composite
{

};

class Album //Composite Composite
{
	public:
		Musica* musicas;
};




class Musica
{


}