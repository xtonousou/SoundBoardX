package io.github.xtonousou.soundboardx;

import java.util.ArrayList;

class ParticleManager {

	private Particle particle;
	private String itemName;
	private ArrayList<Integer> drawableList;

	ParticleManager(Particle particle, String itemName) {
		this.particle = particle;
		this.itemName = itemName;
		this.drawableList = new ArrayList<>();
	}

	void emit() {
		switch (itemName) {
			case "21 Savage: 21":
				drawableList.add(R.drawable.twentyonesavage);
				particle.burstScaleRandomly(drawableList, 10);
				break;
			
			case "420 Blaze It Fa**ot":
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.smokeone);
				particle.burstRandomly(drawableList, 25);
				break;
			
			case "Adam!?!":
				drawableList.add(R.drawable.smokeone);
				particle.burstRandomly(drawableList, 25);
				break;
			
			case "Allahu Akbar":
				drawableList.add(R.drawable.allahuakbar);
				drawableList.add(R.drawable.cfour);
				drawableList.add(R.drawable.isis);
				particle.burstRandomly(drawableList, 15);
				break;
			
			case "Back To The Future: Theme":
				drawableList.add(R.drawable.backtothefuture);
				particle.burstRandomly(drawableList, 10);
				break;
			
			case "Family Guy: Badadipoopi":
				drawableList.add(R.drawable.familyguy);
				drawableList.add(R.drawable.familyguy1);
				particle.burstRandomly(drawableList, 10);
				break;
			
			case "Badum Tss":
				drawableList.add(R.drawable.badumtss);
				particle.burstRandomly(drawableList, 10);
				break;
			
			case "Duke Nukem: Balls Of Steel":
				drawableList.add(R.drawable.ballsofsteel);
				particle.burstRandomly(drawableList, 15);
				break;
			
			case "Bane: Darude Sandstorm":
				drawableList.add(R.drawable.banesandstorm);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Bane: Mask":
				drawableList.add(R.drawable.banesandstorm);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Bang Theory: Bazinga":
				drawableList.add(R.drawable.bazinga);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Bearc*m!!!":
				drawableList.add(R.drawable.ball);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.mtdew);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Beep":
				drawableList.add(R.drawable.beep);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "BEGONE THOT!":
				drawableList.add(R.drawable.begonethot);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Belair: Intro":
				drawableList.add(R.drawable.belair);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: 1234":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: 2 + 2 = 4":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Boom":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Check The Statistics":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Hold Tight Asznee":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Look At Your Nose":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Mans Not Hot":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: No Ketchup":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Ossnaa":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Pa-Pa-Ka-Ka-Ka":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Quick Mafs Full":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Quick Mafs":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Ratnum":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Raw Sauce":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "Big Shaq: Scootnum":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Skidiki-Pa-Pa":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Skrrrrrra":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Skyaaa":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: Smoke Trees":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: You D*ckhead":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Big Shaq":
				drawableList.add(R.drawable.bigshaq);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Black Kid On Fire":
				drawableList.add(R.drawable.kfc);
				drawableList.add(R.drawable.bike);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Bleach: BANKAI":
				drawableList.add(R.drawable.bankai);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Bleach: Senbonzakura Kageyoshi":
				drawableList.add(R.drawable.senbonkageyoshi);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Blend S: Intro":
				drawableList.add(R.drawable.blends);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Blurp":
				drawableList.add(R.drawable.blurp);
				drawableList.add(R.drawable.blurptwo);
				drawableList.add(R.drawable.blurpthree);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Bobby Shmurda: Ah-Ah-Ahhh":
				drawableList.add(R.drawable.shmurda);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Boxeo":
				drawableList.add(R.drawable.boxeo);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Chan Chan":
				drawableList.add(R.drawable.musicnote);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Chan":
				drawableList.add(R.drawable.musicnote);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Star Wars: Chewbacca":
				drawableList.add(R.drawable.chewbacca);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Combo Breaker":
				drawableList.add(R.drawable.combobreakerx2);
				drawableList.add(R.drawable.combobreakerx3);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Come Here My Pet Russian…":
				drawableList.add(R.drawable.csgo);
				drawableList.add(R.drawable.vodka);
				drawableList.add(R.drawable.adidas);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Crash Bandicoot: Aku Aku":
				drawableList.add(R.drawable.akuaku);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Darth Vader: I\'m Your Father":
				drawableList.add(R.drawable.vader);
				drawableList.add(R.drawable.darthvader);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Darth Vader":
				drawableList.add(R.drawable.vader);
				drawableList.add(R.drawable.darthvader);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "De Dotaded Wam":
				drawableList.add(R.drawable.questionmark);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Denzel Curry: I Am The One":
				drawableList.add(R.drawable.denzelcurry);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Desiigner: Kieee":
				drawableList.add(R.drawable.desiigner);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Desiigner: Panda":
				drawableList.add(R.drawable.desiigner);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Desiigner: Rrraa":
				drawableList.add(R.drawable.desiigner);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Dragon Ball: Kamehameha":
				drawableList.add(R.drawable.goku);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Dragon Ball: Over 9000":
				drawableList.add(R.drawable.overninek);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Duck Toy":
				drawableList.add(R.drawable.ducktoy);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "EA Games":
				drawableList.add(R.drawable.eagames);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "EA Sports":
				drawableList.add(R.drawable.easports);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Merlin: Excellent":
				drawableList.add(R.drawable.excellent);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Excuse Me, I Have Some P*ssyhair On Me!":
				drawableList.add(R.drawable.cat);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Fast n\' Furious: Theme":
				drawableList.add(R.drawable.fastnfurious);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Fetty Wap: Squaw":
				drawableList.add(R.drawable.fetty);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Fichtl\'s Lied":
				drawableList.add(R.drawable.fichtlslied);
				drawableList.add(R.drawable.flute);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Filthy Frank: Bing Bing Walla Walla":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Welcome To The Rice Fields":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "FOTNS: NANI?":
				drawableList.add(R.drawable.questionmark);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "FOTNS: Omae Wa Mou Shindeiru":
				drawableList.add(R.drawable.omaewamoushindeiru);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "FOTNS: OWMS Noise":
				drawableList.add(R.drawable.fotnsnoise);
				drawableList.add(R.drawable.fotnsnoise1);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Kazoo Kid: Fun":
				drawableList.add(R.drawable.kazoo);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Game Boy":
				drawableList.add(R.drawable.gameboy);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Get Noscoped":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.snipershot);
				drawableList.add(R.drawable.wombo_combo);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Godzilla":
				drawableList.add(R.drawable.godzilla);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Morgan Freeman: He Knew He F*cked Up":
				drawableList.add(R.drawable.heknewhefuckedup);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Headshot":
				drawableList.add(R.drawable.headshot);
				drawableList.add(R.drawable.snipershot);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Hitler: Nein Nein":
				drawableList.add(R.drawable.nein);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Hitmarker":
				drawableList.add(R.drawable.hitmarker);
				particle.burstRandomly(drawableList, 35);
				break;
				
			case "Holy Sh*t":
				drawableList.add(R.drawable.inception);
				drawableList.add(R.drawable.poop);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "I\'m Growing Stronker!":
				drawableList.add(R.drawable.man);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "iDubbbz: Hey That\' s Pretty Good!":
				drawableList.add(R.drawable.dubz);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Illuminati":
				drawableList.add(R.drawable.illuminati);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Immigrants Cause Cancer":
				drawableList.add(R.drawable.immigrants);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Inception":
				drawableList.add(R.drawable.inception);
				drawableList.add(R.drawable.illuminati);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Inspector Gadget: Theme":
				drawableList.add(R.drawable.inspectorgadget);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Inuyashiki: Bam":
				drawableList.add(R.drawable.inuyashiki);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Inuyashiki: Da-Da-Da":
				drawableList.add(R.drawable.inuyashiki);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "It\' s 04:20 Meng…":
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.smokeone);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Jamie Foxx: Pierre Come Out Here":
				drawableList.add(R.drawable.jamiefoxx);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "John Cena":
				drawableList.add(R.drawable.johncena);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "JoJo: Dorarara":
				drawableList.add(R.drawable.dorarara);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "JoJo: Kono Dio Da!":
				drawableList.add(R.drawable.konodioda);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "JoJo: Muda Muda Muda":
				drawableList.add(R.drawable.zawarudo);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "JoJo: Ora Ora Ora":
				drawableList.add(R.drawable.oraoraora);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "JoJo: ZA WARUDO!":
				drawableList.add(R.drawable.zawarudo);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "Juicy J: Satafaka":
				drawableList.add(R.drawable.juicyj);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Juicy J: Yea Hoe":
				drawableList.add(R.drawable.juicyj);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Shia LaBeouf: JUST DO IT!":
				drawableList.add(R.drawable.justdoit);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Kazoo Kid: Kazoo":
				drawableList.add(R.drawable.kazoo);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Kendrick Lamar: Biaaatch":
				drawableList.add(R.drawable.kendrick);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Law And Order: Theme":
				drawableList.add(R.drawable.lawandorder);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Lil Pump: Esketit":
				drawableList.add(R.drawable.lilpump);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Lil Pump: Gucci Gang":
				drawableList.add(R.drawable.lilpump);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Lil Pump: Laugh":
				drawableList.add(R.drawable.lilpump);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Lil Pump: Seventy Ni**a":
				drawableList.add(R.drawable.lilpump);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Lil Uzi Vert: All My Friends Are Dead":
				drawableList.add(R.drawable.liluzivert);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Lil Uzi Vert: Yah-Yah-Yah":
				drawableList.add(R.drawable.liluzivert);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "LOL":
				drawableList.add(R.drawable.man);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "LOTR: MLG":
				drawableList.add(R.drawable.lotr);
				drawableList.add(R.drawable.mtdew);
				drawableList.add(R.drawable.wombo_combo);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "LOTR: Nazgul":
				drawableList.add(R.drawable.nazgul);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Madeintyo: Skr Skr":
				drawableList.add(R.drawable.madeintyo);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Madeintyo: Skrrt Skrrt":
				drawableList.add(R.drawable.madeintyo);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Mario: Coin":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Mario: Fanfare":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Mario: Grow":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Mario: Jump":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Mario: Lose":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Mario: Underground":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Mario":
				drawableList.add(R.drawable.mario);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Migos: Rain Drop Drop Top":
				drawableList.add(R.drawable.offset);
				drawableList.add(R.drawable.takeoff);
				drawableList.add(R.drawable.quavo);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Mom Get The Camera":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.snipershot);
				drawableList.add(R.drawable.wombo_combo);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "My Mooscles Are Getting Bigger!":
				drawableList.add(R.drawable.man);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Naruto: Katon: Goukakyou No Jutsu":
				drawableList.add(R.drawable.katon);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Naruto: Oodama Rasengan":
				drawableList.add(R.drawable.rasengan);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Naruto: Rasengan":
				drawableList.add(R.drawable.rasengan);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Naruto: Sharingan":
				drawableList.add(R.drawable.sharingan);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Never Done That":
				drawableList.add(R.drawable.inception);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Ni**a Is C*mming…":
				drawableList.add(R.drawable.cum);
				drawableList.add(R.drawable.kfc);
				drawableList.add(R.drawable.bike);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Ni**er\'s Satisfaction":
				drawableList.add(R.drawable.a);
				drawableList.add(R.drawable.watermelon);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Nuke":
				drawableList.add(R.drawable.allahuakbar);
				drawableList.add(R.drawable.bomb);
				particle.burstRandomly(drawableList, 10);
				break;
				
			case "Offset: Offset":
				drawableList.add(R.drawable.offset);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Oh Baby A Triple":
				drawableList.add(R.drawable.babytriple);
				drawableList.add(R.drawable.omg);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Oh My!":
				drawableList.add(R.drawable.ohmy);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Oh Yeaaaah!":
				drawableList.add(R.drawable.ohyeaah);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Pacman Death":
				drawableList.add(R.drawable.pacmandeath);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Oompaloompa":
				drawableList.add(R.drawable.oompaloompa);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Pewdiepie: Intro":
				drawableList.add(R.drawable.ohmahgahpewdiepie);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Pewdiepie: Oh Mah GAH!":
				drawableList.add(R.drawable.ohmahgahpewdiepie);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Filthy Frank: Edgy As F*ck":
				drawableList.add(R.drawable.filthyfrank);
				drawableList.add(R.drawable.edgyasfuck);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Gimme Dat P*ssy":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Nobody Gives A Sh*t":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Nobody Wants You..":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: NYES":
				drawableList.add(R.drawable.filthyfrank);
				drawableList.add(R.drawable.nyes);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Shut The F*ck Up":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Stop Being A F*ckin C*unt":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: Suck My D*ck":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Filthy Frank: You\'re A F*ckin C*nt":
				drawableList.add(R.drawable.filthyfrank);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "Playstation":
				drawableList.add(R.drawable.playstation);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Pokemon: Heal":
				drawableList.add(R.drawable.pokemonheal);
				drawableList.add(R.drawable.pikachu);
				drawableList.add(R.drawable.charmander);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Pokemon: Jigglypuff":
				drawableList.add(R.drawable.jigglypuff);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Pokemon: Our Courage Will Pull Us Through":
				drawableList.add(R.drawable.pokemonheal);
				drawableList.add(R.drawable.pokemonaggouri);
				drawableList.add(R.drawable.pikachu);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Pornhub":
				drawableList.add(R.drawable.pornhub);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Retardation Theme":
				drawableList.add(R.drawable.retard);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Richard Stallman: Windows":
				drawableList.add(R.drawable.stallman);
				drawableList.add(R.drawable.gnu);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Disqualified":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.smwyg);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Get Schwifty":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Look At Meee":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Oh Whee":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.ohwhee);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Okay":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
				
			case "Rick And Morty: SHOW ME WHAT YOU GOT":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.smwyg);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: WLDD":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Russian Father":
				drawableList.add(R.drawable.vodka);
				drawableList.add(R.drawable.adidas);
				drawableList.add(R.drawable.csgo);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Russian\'s Middlefinger":
				drawableList.add(R.drawable.vodka);
				drawableList.add(R.drawable.adidas);
				drawableList.add(R.drawable.csgo);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Yurikuma Arashi: Shabada":
				drawableList.add(R.drawable.cat);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Sad Violin":
				drawableList.add(R.drawable.pepe);
				drawableList.add(R.drawable.whatcha_say);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Seven Vaginias…":
				drawableList.add(R.drawable.cat);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Shots Fired":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.katon);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Shutdown, Glitched…":
				drawableList.add(R.drawable.retard);
				drawableList.add(R.drawable.shutdown);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Skyrim: Fus Ro Dah":
				drawableList.add(R.drawable.skyrim);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Skyrim: Maybe We Could Invite Some Women?":
				drawableList.add(R.drawable.skyrim);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Snoop Dog: Smoke Weed Everyday":
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.smokeone);
				particle.burstRandomly(drawableList, 20);
				break;
				
			case "Sniper Shot":
				drawableList.add(R.drawable.snipershot);
				drawableList.add(R.drawable.omg);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Soulja Boy: Youuuu":
				drawableList.add(R.drawable.soulja);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Spooky Scary Skeletons":
				drawableList.add(R.drawable.skeleton);
				drawableList.add(R.drawable.musicnote);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Squeaker":
				drawableList.add(R.drawable.squeeek);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Steins Gate: Tuturu":
				drawableList.add(R.drawable.tuturu);
				particle.burstScaleRandomly(drawableList, 20);
				break;
				
			case "STFU":
				drawableList.add(R.drawable.stfu);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Street Fighter: Hadouken":
				drawableList.add(R.drawable.hadouken);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Suicide 1":
				drawableList.add(R.drawable.pepe);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "Suicide 2":
				drawableList.add(R.drawable.pepe);
				drawableList.add(R.drawable.musicnote);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "Sup Ni**a, Wanna Talk Some Sh*t?":
				drawableList.add(R.drawable.watermelon);
				drawableList.add(R.drawable.chain);
				drawableList.add(R.drawable.bike);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Surprise Motherf*cker!":
				drawableList.add(R.drawable.surprise);
				particle.burstScaleRandomly(drawableList, 10);
				break;
				
			case "Tetris":
				drawableList.add(R.drawable.tetris);
				drawableList.add(R.drawable.tetris2);
				drawableList.add(R.drawable.tetris3);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "That\'s S 10!":
				drawableList.add(R.drawable.thatsa10);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "The Simpsons: Doh":
				drawableList.add(R.drawable.homer);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "This Is My Masterpiece!":
				drawableList.add(R.drawable.masterpiece);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Rick Harrison: This Is My Pawn Shop":
				drawableList.add(R.drawable.pawnshop);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "Trap Theme":
				drawableList.add(R.drawable.trap);
				drawableList.add(R.drawable.spinme);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Trapaholics: Damn Son!":
				drawableList.add(R.drawable.trapaholics);
				drawableList.add(R.drawable.damnson);
				particle.burstScaleRandomly(drawableList, 15);
				break;
				
			case "Two And A Half Men: Intro":
				drawableList.add(R.drawable.twohalfmen);
				drawableList.add(R.drawable.twohalfmen1);
				drawableList.add(R.drawable.twohalfmen2);
				particle.burstRandomly(drawableList, 15);
				break;
				
			case "U WOT M8":
				drawableList.add(R.drawable.flute);
				drawableList.add(R.drawable.wombo_combo);
				drawableList.add(R.drawable.joint);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Scary Movie: Wazzup":
				drawableList.add(R.drawable.wazzup);
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.smokeone);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Weird Laugh":
				drawableList.add(R.drawable.questionmark);
				drawableList.add(R.drawable.laugh);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "What Else?":
				drawableList.add(R.drawable.retard);
				drawableList.add(R.drawable.shutdown);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "What Is Going On Here?":
				drawableList.add(R.drawable.retard);
				drawableList.add(R.drawable.bekfast);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Skyrim: What Manner Of Sissyness, Is This?":
				drawableList.add(R.drawable.skyrim);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Jason Derulo: Whatcha Say":
				drawableList.add(R.drawable.whatcha_say);
				particle.burstScaleRandomly(drawableList, 25);
				break;
				
			case "Skyrim: Why Don\'t You Show Us That Fine P*ssy?":
				drawableList.add(R.drawable.cat);
				drawableList.add(R.drawable.skyrim);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Windows XP":
				drawableList.add(R.drawable.windows);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Wombo Combo":
				drawableList.add(R.drawable.wombo_combo);
				drawableList.add(R.drawable.combobreakerx2);
				drawableList.add(R.drawable.combobreakerx3);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "WOW":
				drawableList.add(R.drawable.wow);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "WTF BOOM":
				drawableList.add(R.drawable.wtf_boom);
				drawableList.add(R.drawable.allahuakbar);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Yiss":
				drawableList.add(R.drawable.man);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "You Spin Me Round":
				drawableList.add(R.drawable.spinme);
				drawableList.add(R.drawable.trap);
				particle.burstRandomly(drawableList, 25);
				break;
				
			case "Zodiac":
				drawableList.add(R.drawable.fourchan);
				particle.burstRandomly(drawableList, 25);
				break;
		}
		drawableList.clear();
	}
}
