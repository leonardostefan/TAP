
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
	int duracao ;//segundos 
	char nome[STRING_MAX];
	char estilo[STRING_MAX];
	Album* albumPai;
};

