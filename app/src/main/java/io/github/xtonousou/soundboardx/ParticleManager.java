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
				particle.animateAccelerateCircular(drawableList, 10);
				break;
			
			case "420 Blaze It Fa**ot":
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.smokeone);
				particle.animateAnticipateRandomly(drawableList, 25);
				break;
			
			case "Adam!?!":
				drawableList.add(R.drawable.smokeone);
				particle.animateAnticipateRandomly(drawableList, 25);
				break;
			
			case "Allahu Akbar":
				drawableList.add(R.drawable.allahuakbar);
				drawableList.add(R.drawable.cfour);
				drawableList.add(R.drawable.isis);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
			
			case "Back To The Future":
				drawableList.add(R.drawable.backtothefuture);
				particle.animateLinearRandomly(drawableList, 10);
				break;
			
			case "Badadipoopi":
				drawableList.add(R.drawable.badadipoopi);
				particle.animateLinearRandomly(drawableList, 10);
				break;
			
			case "Badum Tss":
				drawableList.add(R.drawable.badumtss);
				particle.animateLinearCircular(drawableList, 10);
				break;
			
			case "Balls Of Steel":
				drawableList.add(R.drawable.ballsofsteel);
				particle.animateLinearCircular(drawableList, 15);
				break;
			
			case "Bane: Darude Sandstorm":
				drawableList.add(R.drawable.banesandstorm);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Bane: Mask":
				drawableList.add(R.drawable.banesandstorm);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Bazinga":
				drawableList.add(R.drawable.bazinga);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Bearc*m!!!":
				drawableList.add(R.drawable.illuminati);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.mtdew);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Beep":
				drawableList.add(R.drawable.beep);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "BEGONE THOT!":
				drawableList.add(R.drawable.girl);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "Belair":
				drawableList.add(R.drawable.belair);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "Big Shaq: 1234":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: 2 + 2 = 4":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Boom":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Check The Statistics":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Hold Tight Asznee":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Look At Your Nose":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Mans Not Hot":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: No Ketchup":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Ossnaa":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Pa-Pa-Ka-Ka-Ka":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Quick Mafs Full":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Quick Mafs":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Ratnum":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Raw Sauce":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "Big Shaq: Scootnum":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Skidiki-Pa-Pa":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Skrrrrrra":
				drawableList.add(R.drawable.bigshaq);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Skyaaa":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: Smoke Trees":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq: You D*ckhead":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Big Shaq":
				drawableList.add(R.drawable.bigshaq);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Black Kid On Fire":
				drawableList.add(R.drawable.kfc);
				drawableList.add(R.drawable.bike);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Bleach: BANKAI":
				drawableList.add(R.drawable.bankai);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Bleach: Senbonzakura Kageyoshi":
				drawableList.add(R.drawable.senbonkageyoshi);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Blend S: Intro":
				drawableList.add(R.drawable.blends);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Blurp":
				drawableList.add(R.drawable.blurp);
				drawableList.add(R.drawable.blurptwo);
				drawableList.add(R.drawable.blurpthree);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "Bobby Shmurda: Ah-Ah-Ahhh":
				drawableList.add(R.drawable.shmurda);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Boxeo":
				drawableList.add(R.drawable.boxeo);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Chan Chan":
				drawableList.add(R.drawable.musicnote);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "Chan":
				drawableList.add(R.drawable.musicnote);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "Chewbacca":
				drawableList.add(R.drawable.chewbacca);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Combo Breaker":
				drawableList.add(R.drawable.combobreakerx2);
				drawableList.add(R.drawable.combobreakerx3);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Come Here My Pet Russian…":
				drawableList.add(R.drawable.csgo);
				drawableList.add(R.drawable.vodka);
				drawableList.add(R.drawable.adidas);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "Crash Bandicoot: Aku Aku":
				drawableList.add(R.drawable.akuaku);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Darth Vader: I\'m Your Father":
				drawableList.add(R.drawable.vader);
				drawableList.add(R.drawable.darthvader);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Darth Vader":
				drawableList.add(R.drawable.vader);
				drawableList.add(R.drawable.darthvader);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "De Dotaded Wam":
				drawableList.add(R.drawable.dedotadedwam);
				particle.animateLinearRandomly(drawableList, 15);
				break;
				
			case "Denzel Curry: I Am The One":
				drawableList.add(R.drawable.denzelcurry);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Desiigner: Kieee":
				drawableList.add(R.drawable.desiigner);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Desiigner: Panda":
				drawableList.add(R.drawable.desiigner);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Desiigner: Rrraa":
				drawableList.add(R.drawable.desiigner);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Dragon Ball: Kamehameha":
				drawableList.add(R.drawable.goku);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Dragon Ball: Over 9000":
				drawableList.add(R.drawable.overninek);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Duck Toy":
				drawableList.add(R.drawable.ducktoy);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "EA Games":
				drawableList.add(R.drawable.eagames);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "EA Sports":
				drawableList.add(R.drawable.easports);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Excellent":
				drawableList.add(R.drawable.excellent);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Excuse Me, I Have Some P*ssyhair On Me!":
				drawableList.add(R.drawable.cat);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "Fast n\' Furious":
				drawableList.add(R.drawable.fastnfurious);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "Fetty Wap: Squaw":
				drawableList.add(R.drawable.fetty);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Fichtl\'s Lied":
				drawableList.add(R.drawable.fichtlslied);
				drawableList.add(R.drawable.flute);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Filthy Frank: Bing Bing Walla Walla":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Filthy Frank: Welcome To The Rice Fields":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "FOTNS: NANI?":
				drawableList.add(R.drawable.fotnsnani);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "FOTNS: Omae Wa Mou Shindeiru":
				drawableList.add(R.drawable.omaewamoushindeiru);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "FOTNS: OWMS Noise":
				drawableList.add(R.drawable.fotnsnoise);
				drawableList.add(R.drawable.fotnsnoise1);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Fun":
				drawableList.add(R.drawable.kazoo);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Game Boy":
				drawableList.add(R.drawable.gameboy);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Get Noscoped":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.snipershot);
				drawableList.add(R.drawable.wombo_combo);
				particle.animateAnticipateRandomly(drawableList, 10);
				break;
				
			case "Godzilla":
				drawableList.add(R.drawable.godzilla);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "He Knew He F*cked Up":
				drawableList.add(R.drawable.heknewhefuckedup);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Headshot":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.snipershot);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Hitler: Nein Nein":
				drawableList.add(R.drawable.nein);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Hitmarker":
				drawableList.add(R.drawable.hitmarker);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "Holy Sh*t":
				drawableList.add(R.drawable.inception);
				drawableList.add(R.drawable.poop);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "I\'m Growing Stronker!":
				drawableList.add(R.drawable.man);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "iDubbbz: Hey That\' s Pretty Good!":
				drawableList.add(R.drawable.dubz);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Illuminati":
				drawableList.add(R.drawable.illuminati);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "Immigrants Cause Cancer":
				drawableList.add(R.drawable.immigrants);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Inception":
				drawableList.add(R.drawable.inception);
				drawableList.add(R.drawable.illuminati);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Inspector Gadget":
				drawableList.add(R.drawable.inspectorgadget);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "Inuyashiki: Bam":
				drawableList.add(R.drawable.inuyashiki);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Inuyashiki: Da-Da-Da":
				drawableList.add(R.drawable.inuyashiki);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "It\' s 04:20 Meng…":
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.smokeone);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "Jamie Foxx: Pierre Come Out Here":
				drawableList.add(R.drawable.jamiefoxx);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "John Cena":
				drawableList.add(R.drawable.johncena);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "JoJo: Dorarara":
				drawableList.add(R.drawable.dorarara);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "JoJo: Kono Dio Da!":
				drawableList.add(R.drawable.konodioda);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "JoJo: Muda Muda Muda":
				drawableList.add(R.drawable.zawarudo);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "JoJo: Ora Ora Ora":
				drawableList.add(R.drawable.oraoraora);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "JoJo: ZA WARUDO!":
				drawableList.add(R.drawable.zawarudo);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "Juicy J: Satafaka":
				drawableList.add(R.drawable.juicyj);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Juicy J: Yea Hoe":
				drawableList.add(R.drawable.juicyj);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "JUST DO IT!":
				drawableList.add(R.drawable.justdoit);
				particle.animateAnticipateCircular(drawableList, 10);
				break;
				
			case "Kazoo":
				drawableList.add(R.drawable.kazoo);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Kendrick Lamar: Biaaatch":
				drawableList.add(R.drawable.kendrick);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Law And Order":
				drawableList.add(R.drawable.lawandorder);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Lil Pump: Esketit":
				drawableList.add(R.drawable.lilpump);
				particle.animateAnticipateCircular(drawableList, 20);
				break;
				
			case "Lil Pump: Gucci Gang":
				drawableList.add(R.drawable.lilpump);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Lil Pump: Laugh":
				drawableList.add(R.drawable.lilpump);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Lil Pump: Seventy Ni**a":
				drawableList.add(R.drawable.lilpump);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Lil Uzi Vert: All My Friends Are Dead":
				drawableList.add(R.drawable.liluzivert);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Lil Uzi Vert: Yah-Yah-Yah":
				drawableList.add(R.drawable.liluzivert);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "LOL":
				drawableList.add(R.drawable.man);
				particle.animateAnticipateCircular(drawableList, 20);
				break;
				
			case "LOTR: MLG":
				drawableList.add(R.drawable.lotr);
				drawableList.add(R.drawable.mtdew);
				drawableList.add(R.drawable.wombo_combo);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "LOTR: Nazgul":
				drawableList.add(R.drawable.nazgul);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Madeintyo: Skr Skr":
				drawableList.add(R.drawable.madeintyo);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Madeintyo: Skrrt Skrrt":
				drawableList.add(R.drawable.madeintyo);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Mario: Coin":
				drawableList.add(R.drawable.mario);
				particle.animateAnticipateRandomly(drawableList, 10);
				break;
				
			case "Mario: Fanfare":
				drawableList.add(R.drawable.mario);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Mario: Grow":
				drawableList.add(R.drawable.mario);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Mario: Jump":
				drawableList.add(R.drawable.mario);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Mario: Lose":
				drawableList.add(R.drawable.mario);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Mario: Underground":
				drawableList.add(R.drawable.mario);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Mario":
				drawableList.add(R.drawable.mario);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Migos: Rain Drop Drop Top":
				drawableList.add(R.drawable.offset);
				drawableList.add(R.drawable.takeoff);
				drawableList.add(R.drawable.quavo);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Mom Get The Camera":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.snipershot);
				drawableList.add(R.drawable.wombo_combo);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "My Mooscles Are Getting Bigger!":
				drawableList.add(R.drawable.man);
				particle.animateLinearRandomly(drawableList, 10);
				break;
				
			case "Naruto: Katon: Goukakyou No Jutsu":
				drawableList.add(R.drawable.katon);
				particle.animateAccelerateRandomly(drawableList, 10);
				break;
				
			case "Naruto: Oodama Rasengan":
				drawableList.add(R.drawable.rasengan);
				particle.animateAccelerateRandomly(drawableList, 20);
				break;
				
			case "Naruto: Rasengan":
				drawableList.add(R.drawable.rasengan);
				particle.animateAccelerateRandomly(drawableList, 20);
				break;
				
			case "Naruto: Sharingan":
				drawableList.add(R.drawable.sharingan);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Never Done That":
				drawableList.add(R.drawable.inception);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Ni**a Is C*mming…":
				drawableList.add(R.drawable.cum);
				drawableList.add(R.drawable.kfc);
				drawableList.add(R.drawable.bike);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "Ni**er\'s Satisfaction":
				drawableList.add(R.drawable.a);
				drawableList.add(R.drawable.a2);
				drawableList.add(R.drawable.watermelon);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "Nuke":
				drawableList.add(R.drawable.allahuakbar);
				drawableList.add(R.drawable.bomb);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Offset: Offset":
				drawableList.add(R.drawable.offset);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Oh Baby A Triple":
				drawableList.add(R.drawable.babytriple);
				drawableList.add(R.drawable.omg);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "Oh My!":
				drawableList.add(R.drawable.ohmy);
				particle.animateLinearCircular(drawableList, 10);
				break;
				
			case "Oh Yeaaaah!":
				drawableList.add(R.drawable.ohyeaah);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Pacman Death":
				drawableList.add(R.drawable.pacmandeath);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "Oompaloompa":
				drawableList.add(R.drawable.oompaloompa);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Pewdiepie: Intro":
				drawableList.add(R.drawable.ohmahgahpewdiepie);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Pewdiepie: Oh Mah GAH!":
				drawableList.add(R.drawable.ohmahgahpewdiepie);
				particle.animateAnticipateCircular(drawableList, 15);
				break;
				
			case "Pink Guy: Edgy As F*ck":
				drawableList.add(R.drawable.filthyfrank);
				drawableList.add(R.drawable.edgyasfuck);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: Gimme Dat P*ssy":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: Nobody Gives A Sh*t":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: Nobody Wants You..":
				drawableList.add(R.drawable.fichtlslied);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: NYES":
				drawableList.add(R.drawable.fichtlslied);
				particle.animateAccelerateRandomly(drawableList, 20);
				break;
				
			case "Pink Guy: Shut The F*ck Up":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: Stop Being A F*ckin C*unt":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: Suck My D*ck":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Pink Guy: You\'re A F*ckin C*nt":
				drawableList.add(R.drawable.filthyfrank);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Playstation":
				drawableList.add(R.drawable.playstation);
				particle.animateLinearRandomly(drawableList, 20);
				break;
				
			case "Pokemon: Heal":
				drawableList.add(R.drawable.pokemonheal);
				drawableList.add(R.drawable.pikachu);
				drawableList.add(R.drawable.charmander);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Pokemon: Jigglypuff":
				drawableList.add(R.drawable.jigglypuff);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Pokemon: Our Courage Will Pull Us Through":
				drawableList.add(R.drawable.pokemonheal);
				drawableList.add(R.drawable.pokemonaggouri);
				drawableList.add(R.drawable.pikachu);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Pornhub":
				drawableList.add(R.drawable.pornhub);
				particle.animateLinearCircular(drawableList, 20);
				break;
				
			case "Retardation Theme":
				drawableList.add(R.drawable.retard);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Richard Stallman":
				drawableList.add(R.drawable.windows);
				drawableList.add(R.drawable.gnu);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Rick And Morty: Disqualified":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.smwyg);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Get Schwifty":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Look At Meee":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Oh Whee":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.ohwhee);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Rick And Morty: Okay":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
				
			case "Rick And Morty: SHOW ME WHAT YOU GOT":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.smwyg);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Rick And Morty: WLDD":
				drawableList.add(R.drawable.rick);
				drawableList.add(R.drawable.morty);
				drawableList.add(R.drawable.meeseeks);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Russian Father":
				drawableList.add(R.drawable.vodka);
				drawableList.add(R.drawable.adidas);
				drawableList.add(R.drawable.csgo);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Russian\'s Middlefinger":
				drawableList.add(R.drawable.vodka);
				drawableList.add(R.drawable.adidas);
				drawableList.add(R.drawable.csgo);
				particle.animateLinearCircular(drawableList, 15);
				break;
				
			case "Sabada":
				drawableList.add(R.drawable.cat);
				particle.animateLinearRandomly(drawableList, 15);
				break;
				
			case "Sad Violin":
				drawableList.add(R.drawable.pepe);
				drawableList.add(R.drawable.whatcha_say);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "Seven Vaginias…":
				drawableList.add(R.drawable.cat);
				particle.animateLinearRandomly(drawableList, 20);
				break;
				
			case "Shots Fired":
				drawableList.add(R.drawable.omg);
				drawableList.add(R.drawable.katon);
				particle.animateAnticipateRandomly(drawableList, 20);
				break;
				
			case "Shutdown, Glitched…":
				drawableList.add(R.drawable.retard);
				drawableList.add(R.drawable.shutdown);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "Skyrim: Fus Ro Dah":
				drawableList.add(R.drawable.skyrim);
				drawableList.add(R.drawable.fusrodah);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Skyrim: Maybe We Could Invite Some Women?":
				drawableList.add(R.drawable.skyrim);
				particle.animateAccelerateCircular(drawableList, 20);
				break;
				
			case "Smoke Weed Everyday":
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.joint);
				drawableList.add(R.drawable.smokeone);
				particle.animateAnticipateCircular(drawableList, 20);
				break;
				
			case "Sniper Shot":
				drawableList.add(R.drawable.snipershot);
				drawableList.add(R.drawable.omg);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "Soulja Boy: Youuuu":
				drawableList.add(R.drawable.soulja);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Spooky Scary Skeletons":
				drawableList.add(R.drawable.skeleton);
				drawableList.add(R.drawable.musicnote);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Squeaker":
				drawableList.add(R.drawable.squeeek);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "Steins Gate: Tuturu":
				drawableList.add(R.drawable.tuturu);
				particle.animateLinearCircular(drawableList, 20);
				break;
				
			case "STFU":
				drawableList.add(R.drawable.stfu);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "Street Fighter: Hadouken":
				drawableList.add(R.drawable.hadouken);
				particle.animateLinearRandomly(drawableList, 15);
				break;
				
			case "Suicide 1":
				drawableList.add(R.drawable.pepe);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "Suicide 2":
				drawableList.add(R.drawable.pepe);
				drawableList.add(R.drawable.musicnote);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "Sup Ni**a, Wanna Talk Some Sh*t?":
				drawableList.add(R.drawable.watermelon);
				drawableList.add(R.drawable.chain);
				drawableList.add(R.drawable.bike);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "Surprise Motherf*cker!":
				drawableList.add(R.drawable.surprise);
				particle.animateAccelerateCircular(drawableList, 10);
				break;
				
			case "Tetris":
				drawableList.add(R.drawable.tetris);
				drawableList.add(R.drawable.tetris2);
				drawableList.add(R.drawable.tetris3);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "That\'s S 10!":
				drawableList.add(R.drawable.thatsa10);
				particle.animateLinearRandomly(drawableList, 15);
				break;
				
			case "The Simpsons: Doh":
				drawableList.add(R.drawable.homer);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "This Is My Masterpiece!":
				drawableList.add(R.drawable.masterpiece);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "This Is My Pawn Shop":
				drawableList.add(R.drawable.pawnshop);
				particle.animateAccelerateRandomly(drawableList, 15);
				break;
				
			case "Trap Theme":
				drawableList.add(R.drawable.trap);
				drawableList.add(R.drawable.spinme);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "Trapaholics: Damn Son!":
				drawableList.add(R.drawable.trapaholics);
				drawableList.add(R.drawable.damnson);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "Two And A Half Men":
				drawableList.add(R.drawable.twohalfmen);
				drawableList.add(R.drawable.musicnote);
				particle.animateAccelerateCircular(drawableList, 15);
				break;
				
			case "U WOT M8":
				drawableList.add(R.drawable.flute);
				drawableList.add(R.drawable.wombo_combo);
				drawableList.add(R.drawable.joint);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "Wazzup":
				drawableList.add(R.drawable.wazzup);
				drawableList.add(R.drawable.weed);
				drawableList.add(R.drawable.smokeone);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "Weird Laugh":
				drawableList.add(R.drawable.dedotadedwam);
				drawableList.add(R.drawable.laugh);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "What Else?":
				drawableList.add(R.drawable.retard);
				drawableList.add(R.drawable.shutdown);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "What Is Going On Here?":
				drawableList.add(R.drawable.retard);
				drawableList.add(R.drawable.bekfast);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "What Manner Of Sissyness, Is This?":
				drawableList.add(R.drawable.skyrim);
				drawableList.add(R.drawable.poop);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "Whatcha Say":
				drawableList.add(R.drawable.whatcha_say);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "Why Don\'t You Show Us That Fine P*ssy?":
				drawableList.add(R.drawable.cat);
				drawableList.add(R.drawable.skyrim);
				particle.animateLinearRandomly(drawableList, 25);
				break;
				
			case "Windows XP":
				drawableList.add(R.drawable.windows);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "Wombo Combo":
				drawableList.add(R.drawable.wombo_combo);
				drawableList.add(R.drawable.combobreakerx2);
				drawableList.add(R.drawable.combobreakerx3);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
				
			case "WOW":
				drawableList.add(R.drawable.wow);
				particle.animateAnticipateCircular(drawableList, 25);
				break;
				
			case "WTF BOOM":
				drawableList.add(R.drawable.wtf_boom);
				drawableList.add(R.drawable.allahuakbar);
				particle.animateAnticipateRandomly(drawableList, 25);
				break;
				
			case "Yiss":
				drawableList.add(R.drawable.yiiisss);
				particle.animateLinearCircular(drawableList, 25);
				break;
				
			case "You Spin Me Round":
				drawableList.add(R.drawable.spinme);
				drawableList.add(R.drawable.trap);
				particle.animateAccelerateRandomly(drawableList, 25);
				break;
				
			case "Zodiac":
				drawableList.add(R.drawable.fourchan);
				particle.animateLinearCircular(drawableList, 25);
				break;
		}
		drawableList.clear();
	}
}
