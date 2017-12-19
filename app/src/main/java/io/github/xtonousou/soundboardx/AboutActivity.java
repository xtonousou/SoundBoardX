package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String author = getString(R.string.author);

		View aboutPage = new AboutPage(AboutActivity.this)
				.isRTL(false)
				.setCustomFont(getString(R.string.roboto_r))
				.setDescription(getString(R.string.app_description))
				.setImage(R.mipmap.ic_launcher)
				.addGroup(getString(R.string.connect))
				.addEmail(getString(R.string.mail))
				.addWebsite(getString(R.string.website))
				.addGitHub(author + File.separator + getString(R.string.app_name))
				//.addFacebook(author)
				.addTwitter(author)
				.addInstagram(author)
				.addItem(getCopyRightsElement())
				.create();

		setContentView(aboutPage);

		//TODO Add toolbar with back button and remove NullPointerException
		//getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	Element getCopyRightsElement() {
		Element copyRightsElement = new Element();
		copyRightsElement.setTitle(String.format(getString(R.string.copy_right),
				Calendar.getInstance().get(Calendar.YEAR)));
		copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
		copyRightsElement.setIconNightTint(R.color.colorAccent);
		copyRightsElement.setGravity(Gravity.CENTER);
		copyRightsElement.setOnClickListener(view ->
				Toast.makeText(AboutActivity.this, R.string.license, Toast.LENGTH_SHORT)
						.show());
		return copyRightsElement;
	}
}
