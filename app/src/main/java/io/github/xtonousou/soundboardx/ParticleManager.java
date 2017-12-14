package io.github.xtonousou.soundboardx;

import java.util.ArrayList;

class ParticleManager {

	private Particle particle;
	private String itemName;

	ParticleManager(Particle particle, String itemName) {
		this.particle = particle;
		this.itemName = itemName;
	}

	void emit() {
		ArrayList<Integer> drawableList = new ArrayList<>();
		switch (itemName) {
			case "Hitmarker":
				drawableList.add(R.drawable.hitmarker);
				particle.animateAccelerateCircular(drawableList, 25);
				break;
		}
		drawableList.clear();
	}
}
