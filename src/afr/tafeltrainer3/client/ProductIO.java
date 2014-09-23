package afr.tafeltrainer3.client;

import afr.tafeltrainer3.client.shop.Productcategorie;
import afr.tafeltrainer3.shared.Product;

public class ProductIO 
{

	public static Product getProduct(int index)	
	{
	switch (index)
	{
	case 1 : return new Product(index,"/images/shop/stripfiguren/snoopy.png",40,Productcategorie.cartoons);
	
	case 2 : return new Product(index,"/images/shop/stripfiguren/spongebob.png",40,Productcategorie.cartoons);
	
	case 3 : return new Product(index,"/images/shop/plaatje3.png",30,Productcategorie.dieren);
	
	case 4 : return new Product(index,"/images/shop/autos/alfaromeo.png",45,Productcategorie.autos);
	
	case 5 : return new Product(index,"/images/shop/autos/audi.png",100,Productcategorie.autos);
	
	case 6 : return new Product(index,"/images/shop/autos/bentley.png",200,Productcategorie.autos);
	
	case 7 : return new Product(index,"/images/shop/autos/bmw.png",125,Productcategorie.autos);
	
	case 8 : return new Product(index,"/images/shop/edelstenen/amethist.png",80,Productcategorie.edelstenen);

	case 9 : return new Product(index,"/images/shop/edelstenen/diamant.png",250,Productcategorie.edelstenen);

	case 10 : return new Product(index,"/images/shop/edelstenen/goud.png",150,Productcategorie.edelstenen);
	
	case 11 : return new Product(index,"/images/shop/edelstenen/kristal.png",60,Productcategorie.edelstenen);
	
	case 12 : return new Product(index,"/images/shop/edelstenen/robijn.png",100,Productcategorie.edelstenen);
	
	case 13 : return new Product(index,"/images/shop/edelstenen/saffier.png",200,Productcategorie.edelstenen);
	
	case 14 : return new Product(index,"/images/shop/edelstenen/smaragd.png",175,Productcategorie.edelstenen);
	
	case 15 : return new Product(index,"/images/shop/landdieren1/egelbaby.png",25,Productcategorie.landdieren);
	
	case 16 : return new Product(index,"/images/shop/landdieren1/gekko.png",35,Productcategorie.landdieren);
	
	case 17 : return new Product(index,"/images/shop/landdieren1/gorilla.png",55,Productcategorie.landdieren);
	
	case 18 : return new Product(index,"/images/shop/landdieren1/leguaan.png",45,Productcategorie.landdieren);
	
	case 19 : return new Product(index,"/images/shop/landdieren1/rhinoceros.png",50,Productcategorie.landdieren);
	
	case 20 : return new Product(index,"/images/shop/landdieren1/rups.png",30,Productcategorie.landdieren);
	
	case 21 : return new Product(index,"/images/shop/landdieren1/slang.png",40,Productcategorie.landdieren);
	
	case 22 : return new Product(index,"/images/shop/landdieren1/tijger.png",60,Productcategorie.landdieren);
	
	case 23 : return new Product(index,"/images/shop/landdieren1/walrus.png",65,Productcategorie.landdieren);
	
	case 24 : return new Product(index,"/images/shop/landdieren1/zeehondpuppy.png",70,Productcategorie.landdieren);
	
	case 25 : return new Product(index,"/images/shop/luchtddieren/kaketoe.png",25,Productcategorie.luchtdieren);
	
	case 26 : return new Product(index,"/images/shop/luchtddieren/kolibri.png",35,Productcategorie.luchtdieren);
	
	case 27 : return new Product(index,"/images/shop/luchtddieren/papegaai.png",45,Productcategorie.luchtdieren);
	
	case 28 : return new Product(index,"/images/shop/luchtddieren/pauw.png",55,Productcategorie.luchtdieren);
	
	case 29 : return new Product(index,"/images/shop/luchtddieren/roodkuifpapegaai.png",30,Productcategorie.luchtdieren);
	
	case 30 : return new Product(index,"/images/shop/luchtddieren/uil.png",65,Productcategorie.luchtdieren);
	
	case 31 : return new Product(index,"/images/shop/luchtddieren/uilskuiken.png",75,Productcategorie.luchtdieren);
	
	case 32 : return new Product(index,"/images/shop/luchtddieren/zwaluw.png",40,Productcategorie.luchtdieren);
	
	case 33 : return new Product(index,"/images/shop/zeedieren/axolotl.png",25,Productcategorie.zeedieren);
	
	case 34 : return new Product(index,"/images/shop/zeedieren/octopus.png",45,Productcategorie.zeedieren);
	
	case 35 : return new Product(index,"/images/shop/zeedieren/zeehond.png",65,Productcategorie.zeedieren);
	
	case 36 : return new Product(index,"/images/shop/zeedieren/zeeschildpad.png",75,Productcategorie.zeedieren);
	
	case 37 : return new Product(index,"/images/shop/autos/bugatti.png",50,Productcategorie.autos);
	
	case 38 : return new Product(index,"/images/shop/autos/buick.png",75,Productcategorie.autos);
	
	case 39 : return new Product(index,"/images/shop/autos/chevrolet.png",85,Productcategorie.autos);
	
	case 40 : return new Product(index,"/images/shop/autos/lexus.png",125,Productcategorie.autos);
	
	case 41 : return new Product(index,"/images/shop/autos/mercedes.png",95,Productcategorie.autos);
	
	case 42 : return new Product(index,"/images/shop/autos/pontiac.png",125,Productcategorie.autos);
	
	case 43 : return new Product(index,"/images/shop/autos/porsche.png",150,Productcategorie.autos);

	case 44 : return new Product(index,"/images/shop/autos/rollsroyce.png",225,Productcategorie.autos);
	
	case 45 : return new Product(index,"/images/shop/zeedieren/dolfijn.png",65,Productcategorie.zeedieren);

	case 46 : return new Product(index,"/images/shop/zeedieren/koraalduivel.png",35,Productcategorie.zeedieren);

	case 47 : return new Product(index,"/images/shop/zeedieren/mandarijnvis.png",75,Productcategorie.zeedieren);

	case 48 : return new Product(index,"/images/shop/zeedieren/papegaaivis.png",60,Productcategorie.zeedieren);

	case 49 : return new Product(index,"/images/shop/zeedieren/pincetvis.png",50,Productcategorie.zeedieren);
	
	case 50 : return new Product(index,"/images/shop/voetballers/Arjen_Robben.png",30,Productcategorie.voetballers);
	
	case 51 : return new Product(index,"/images/shop/voetballers/Jordy_Clasie.png",25,Productcategorie.voetballers);
	
	case 52 : return new Product(index,"/images/shop/voetballers/Zlatan_Ibrahimovic.png",75,Productcategorie.voetballers);
	
	case 53 : return new Product(index,"/images/shop/voetballers/Lionel_Messi.png",100,Productcategorie.voetballers);
	
	case 54 : return new Product(index,"/images/shop/voetballers/Robert_Lewandowski.png",95,Productcategorie.voetballers);
	
	case 55 : return new Product(index,"/images/shop/voetballers/Robin_van_Persie.png",60,Productcategorie.voetballers);
	
	case 56 : return new Product(index,"/images/shop/voetballers/Joe_Hart.png",50,Productcategorie.voetballers);
	
	case 57 : return new Product(index,"/images/shop/voetballers/Luis_Suares.png",90,Productcategorie.voetballers);
	
	case 58 : return new Product(index,"/images/shop/voetballers/Karim_Benzema.png",35,Productcategorie.voetballers);
	
	case 59 : return new Product(index,"/images/shop/voetballers/Daley_Blind.png",40,Productcategorie.voetballers);
	
	case 60 : return new Product(index,"/images/shop/voetballers/Cristiano_Ronaldo.png",80,Productcategorie.voetballers);
	
	case 61 : return new Product(index,"/images/shop/voetballers/Gareth_Bale.png",65,Productcategorie.voetballers);
	
	case 62 : return new Product(index,"/images/shop/voetballers/Eden_Hazard.png",75,Productcategorie.voetballers);

	case 63 : return new Product(index,"/images/shop/grappig/beren_op_paarden.png",25,Productcategorie.grappig);

	case 64 : return new Product(index,"/images/shop/grappig/boze_uil.png",25,Productcategorie.grappig);

	case 65 : return new Product(index,"/images/shop/grappig/emoe.png",40,Productcategorie.grappig);
	
	case 66 : return new Product(index,"/images/shop/grappig/geit_achterop_fiets.png",40,Productcategorie.grappig);
	
	case 67 : return new Product(index,"/images/shop/grappig/grappig_aapje.png",50,Productcategorie.grappig);
	
	case 68 : return new Product(index,"/images/shop/grappig/hond_in_koelkast.png",60,Productcategorie.grappig);
	
	case 69 : return new Product(index,"/images/shop/grappig/hond_met_bril.png",60,Productcategorie.grappig);
	
	case 70 : return new Product(index,"/images/shop/grappig/poes_in_wc.png",65,Productcategorie.grappig);
	
	case 71 : return new Product(index,"/images/shop/grappig/poes_wasbeertjes.png",65,Productcategorie.grappig);
	
	case 72 : return new Product(index,"/images/shop/grappig/poes.png",70,Productcategorie.grappig);
	
	case 73 : return new Product(index,"/images/shop/grappig/verklede_hond.png",70,Productcategorie.grappig);
	
	case 74 : return new Product(index,"/images/shop/wereldsteden/amsterdam.png",40,Productcategorie.wereldsteden);
	
	case 75 : return new Product(index,"/images/shop/wereldsteden/beijing.png",40,Productcategorie.wereldsteden);
	
	case 76 : return new Product(index,"/images/shop/wereldsteden/istanboel.png",45,Productcategorie.wereldsteden);
	
	case 77 : return new Product(index,"/images/shop/wereldsteden/londen.png",45,Productcategorie.wereldsteden);
	
	case 78 : return new Product(index,"/images/shop/wereldsteden/mexicocity.png",50,Productcategorie.wereldsteden);
	
	case 79 : return new Product(index,"/images/shop/wereldsteden/moskou.png",50,Productcategorie.wereldsteden);
	
	case 80 : return new Product(index,"/images/shop/wereldsteden/riodejaneiro.png",55,Productcategorie.wereldsteden);
	
	case 81 : return new Product(index,"/images/shop/wereldsteden/sidney.png",55,Productcategorie.wereldsteden);
	
	case 82 : return new Product(index,"/images/shop/wereldsteden/singapore.png",60,Productcategorie.wereldsteden);
	
	case 83 : return new Product(index,"/images/shop/wereldsteden/parijs.png",60,Productcategorie.wereldsteden);
	
	case 84 : return new Product(index,"/images/shop/stripfiguren/asterix.png",40,Productcategorie.cartoons);

	case 85 : return new Product(index,"/images/shop/stripfiguren/donald_duck.png",45,Productcategorie.cartoons);
	
	case 86 : return new Product(index,"/images/shop/stripfiguren/garfield.png",45,Productcategorie.cartoons);
	
	case 87 : return new Product(index,"/images/shop/stripfiguren/homersimpson.png",50,Productcategorie.cartoons);
	
	case 88 : return new Product(index,"/images/shop/stripfiguren/lucky_luke.png",50,Productcategorie.cartoons);
	
	case 89 : return new Product(index,"/images/shop/stripfiguren/rozepanter.png",55,Productcategorie.cartoons);
	
	case 90 : return new Product(index,"/images/shop/stripfiguren/scoobydoo.png",55,Productcategorie.cartoons);
	
	case 91 : return new Product(index,"/images/shop/stripfiguren/shrek.png",60,Productcategorie.cartoons);
	
	//case 92 : return new Product(index,"/images/shop/stripfiguren/snoopy.png",40,Productcategorie.cartoons);
	
	//case 93 : return new Product(index,"/images/shop/stripfiguren/spongebob.png",40,Productcategorie.cartoons);
	
	case 99 : return new Product(index,"/images/shop/wereldsteden/amsterdam.png",40,Productcategorie.grappig);

	default : return new Product(0,"niet in database",99,Productcategorie.cartoons);
	
	
	
	

	}
	
	}

}
