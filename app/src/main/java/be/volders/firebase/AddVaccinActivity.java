package be.volders.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import be.volders.firebase.models.Vaccin;

public class AddVaccinActivity extends AppCompatActivity {

    private static final String TITLE = "Vaccin toevoegen";

    String TAG = "AddVAccinActivity";
    Vaccin vaccin;
    EditText txtVaccinName;
    EditText txtZiekteNaam;
    Button btnSaveVaccin;
    Button btnShowListVaccins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccin);

        Objects.requireNonNull(getSupportActionBar()).setTitle(TITLE);

        vaccin = new Vaccin();
        final Intent i = new Intent(this, ListBasicVaccinActivity.class);

// ----------------------- UI SETUP -----------------------
        txtVaccinName = findViewById(R.id.txtVaccinName);
        txtZiekteNaam = findViewById(R.id.txtZiekteNaam);
        btnSaveVaccin = findViewById(R.id.btnSaveVaccin);
        btnShowListVaccins = findViewById(R.id.btnShowVaccinList);

        // ----------------------- DATABASE SETUP -----------------------
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        final DatabaseReference vaccinRef = database.getReference().child("vaccin");
        // ----------------------- INITIAL LOAD: ALL VACCINS -----------------------
        vaccin = new Vaccin("IPV");
        /*
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
         */

        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("3");
        vaccin.addMaandInterval("4");

        vaccin.addMaandInterval("13");
        vaccin.addMaandInterval("15");
        // vaccin.addJaarInterval(5, 6);
        vaccin.addZiekte("Poliomyelitis");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("Hib");
        vaccin.addVaccin("HBV");
        vaccin.setInfo("De basispoliovaccinatie gebeurt met een geïnactiveerd poliovaccin (IPV) dat deel uitmaakt van het gecombineerd hexavalent DTPa-IPV-Hib-HBV-vaccin. Het schema voor dit gecombineerd vaccin omvat 3 dosissen met telkens vier weken interval tijdens het eerste levensjaar en één booster op de leeftijd van 13-15 maanden. In het polio-attest voor de gemeente moeten in dit geval alleen de data vermeld worden van de eerste, derde en vierde dosis.\n" +
                "De boostervaccinatie op 5 - 6 jaar wordt uitgevoerd met een tetravalent DTPa-IPV- vaccin. Indien enkel met het IPV gevaccineerd wordt, zijn 3 dosissen voldoende: het interval tussen de eerste 2 dosissen bedraagt dan minstens 8 weken, en de 3de dosis wordt toegediend op de leeftijd van 12-18 maanden (HGR 9208A).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("DTPa");
        /*
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
        */

        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("3");
        vaccin.addMaandInterval("4");

        vaccin.addMaandInterval("13");
        vaccin.addMaandInterval("15");
        // vaccin.addJaarInterval(5, 6);
        vaccin.addZiekte("Difterie");
        vaccin.addZiekte("Tetanus");
        vaccin.addZiekte("Kinkhoest");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("Hib");
        vaccin.addVaccin("HBV");
        vaccin.setInfo("Basisvaccinatie tegen Difterie, Tetanus en Kinkhoest gebeurt met een gecombineerd Basisvaccinatie DTPa-HBV-IPV-Hib-vaccin.\n" +
                "De boostervaccinatie gebeurt op 5 tot 6 jaar met een tetravalent DTPa-IPV- vaccin. Op de leeftijd van 15 -16 jaar (adolescenten), voor de zwangere vrouw, tijdens iedere zwangerschap, en voor volwassenen (om de 10 jaar), vindt een herhalingsvaccinatie met een trivalent dTpa-vaccin. Deze gecombineerde vaccins bevatten allen een acellullair kinkhoestvaccin (HGR 8807");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("dTpa");
        vaccin.addJaarInterval("15");
        vaccin.addJaarInterval("16");
        //vaccin.addJaarInterval(">= 25 jaar en elke 10 jaren");
        //vaccin.addJaarInterval(">= 65 jaar");
        // vaccin.addJaarInterval("Tijdens zwangerschap");
        vaccin.addZiekte("Difterie");
        vaccin.addZiekte("Tetanus");
        vaccin.addZiekte("Kinkhoest");
        vaccin.setBoolZwangereVrouw(true);
        vaccin.setInfo("Basisvaccinatie tegen Difterie, Tetanus en Kinkhoest gebeurt met een gecombineerd Basisvaccinatie DTPa-HBV-IPV-Hib-vaccin.\n" +
                "De boostervaccinatie gebeurt op 5 tot 6 jaar met een tetravalent DTPa-IPV- vaccin. Op de leeftijd van 15 -16 jaar (adolescenten), voor de zwangere vrouw, tijdens iedere zwangerschap, en voor volwassenen (om de 10 jaar), vindt een herhalingsvaccinatie met een trivalent dTpa-vaccin. Deze gecombineerde vaccins bevatten allen een acellullair kinkhoestvaccin (HGR 8807");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("Hib");
        /*
        vaccin.addWeekInterval(8);
        vaccin.addWeekInterval(12);
        vaccin.addWeekInterval(16);
        */

        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("3");
        vaccin.addMaandInterval("4");

        vaccin.addMaandInterval("13");
        vaccin.addMaandInterval("15");
        vaccin.addZiekte("Haemophilus");
        vaccin.addZiekte("Influenzae");
        vaccin.addZiekte("type b (Hib)");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("HBV");
        vaccin.setInfo("Vaccinatie tegen Haemophilus influenzae type b gebeurt met een gecombineerd hexavalent DTPa-IPV-Hib-HBV-vaccin (cfr 2). In geval van primovaccinatie na de leeftijd van 12 maanden volstaat één dosis. Inhaalvaccinatie is niet meer zinvol na de leeftijd van 2 jaar (HGR 8808).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("HBV");
        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("3");
        vaccin.addMaandInterval("4");

        vaccin.addMaandInterval("13");
        vaccin.addMaandInterval("15");
        vaccin.addZiekte("Hepatitis B");
        vaccin.addVaccin("IPV");
        vaccin.addVaccin("DTPa");
        vaccin.addVaccin("Hib");
        vaccin.setInfo("De vaccinatie van de zuigeling tegen hepatitis B gebeurt met een gecombineerd hexavalent DTPa-IPV-Hib-HBV-vaccin (cfr.2). Na afwerken van dit bassisschema (4 dosissen) wordt geen verdere boostervaccinatie aanbevolen .\n" +
                "Voor kinderen van een moeder die HBsAg draagster is, wordt een extra geboortedosis aanbevolen (HGR 8809).");

        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);


        vaccin = new Vaccin("MBR");
        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("3");
        vaccin.addMaandInterval("4");

        vaccin.addMaandInterval("13");
        vaccin.addMaandInterval("15");
        vaccin.addZiekte("Mazelen");
        vaccin.addZiekte("Bof");
        vaccin.addZiekte("Rubella");
        vaccin.setInfo("De MBR-vaccinatie omvat 2 dosissen: op 12 maanden(MBR1) en op 7-9 jaar (MBR2).\n" +
                "Het Nationaal Comité voor de eliminatie van mazelen pleit voor de verlaging van de leeftijd van de tweede dosis van MBR, dit om de eliminatie van mazelen in ons land beter te controleren. Dit is de reden waarom de HGR, vanaf 2019, de dosis van de MBR2 op 7-9 jaar aanbeveelt in plaats van op 10-13 jaar.\n" +
                "De doelstelling om mazelen te elimineren in Europa kan slechts bereikt worden als de vaccinatiegraad voor beide dosissen minstens 95% is (HGR 8811).");

        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);


        vaccin = new Vaccin("MenC");
        vaccin.addMaandInterval("13");
        vaccin.addMaandInterval("15");
        vaccin.addZiekte("Meningokok C");
        vaccin.setInfo("De vaccinatie tegen meningokokken van serogroep C (MenC) wordt éénmalig op de leeftijd van 13-15 maanden aanbevolen, met een geconjugeerd vaccin (HGR 8810).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);


        vaccin = new Vaccin("PCV13");
        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("4");
        vaccin.addMaandInterval("12");
        vaccin.addMaandInterval("780");
        // vaccin.addJaarInterval(">=65");
        vaccin.addZiekte("Pneumokok");
        vaccin.setInfo("De vaccinatie tegen pneumokokken door middel van het PCV13-vaccin omvat 3 dosissen volgens het 2+1 schema. Tussen de dosissen wordt een interval van 8 weken gerespecteerd, de derde dosis volgt zo vroeg mogelijk in het tweede levensjaar, bij voorkeur op de leeftijd van 12 maanden (HGR 9519).\n" +
                "De keuze voor het pneumokokkenvaccin is gebaseerd op de epidemiologie van invasieve pneumokokkeninfecties. Voor 2019 werd hierdoor de voorkeur gegeven voor het PCV13 vaccin.\n" +
                "Inhaalvaccinatie na de leeftijd van 24 maanden is niet zinvol, tenzij bij kinderen die een verhoogd risico op een invasieve pneumokokken-infectie lopen..\n" +
                "- Vaccin tegen pneumokokken bij volwassenen vanaf 65 jaar\n" +
                "Pneumokokkenvaccinatie wordt aanbevolen voor alle volwassenen ouder dan 65 jaar. Een primovaccinatie met het geconjugeerde vaccin PCV13, gevolgd door een dosis van polysaccharide-vaccin PCV23 na minimaal 8 weken wordt aanbevolen (HGR 9210).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("PCV23");
        // vaccin.addJaarInterval(">=65");
        vaccin.addMaandInterval("780");
        vaccin.addZiekte("Pneumokok");
        vaccin.setInfo("Pneumokokkenvaccinatie wordt aanbevolen voor alle volwassenen ouder dan 65 jaar. Een primovaccinatie met het geconjugeerde vaccin PCV13, gevolgd door een dosis van polysaccharide-vaccin PCV23 na minimaal 8 weken wordt aanbevolen (HGR 9210).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("Rota");
        vaccin.addMaandInterval("2");
        vaccin.addMaandInterval("3");
        vaccin.addMaandInterval("4");
        vaccin.addZiekte("Rotavirus");
        vaccin.setInfo("Het peroraal toe te dienen rotavirus vaccin wordt aangeraden bij voor alle zuigelingen; de volledige vaccinatie moet vóór de leeftijd van zes maanden afgerond te zijn. Naargelang het gebruikte vaccin zal het schema bestaan uit 2 dosissen (Rotarix®) of 3 dosissen (RotaTeq®). Na deze leeftijd wordt geen enkele inhaalvaccinatie van het rotavaccin aanbevolen (HGR 8812).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("HPV");
        vaccin.addMaandInterval("132");
        vaccin.addMaandInterval("144");
        vaccin.addMaandInterval("156");

        vaccin.addZiekte("Humaan Papillomavirus");
        vaccin.setInfo("Jaarlijkse algemene profylactische vaccinatie van een cohorte van meisjes en jongens van 9 tot en met 14 jaar wordt aanbevolen volgens een schema met 2 dosissen van een adequaat HPV-vaccin (0-6 maanden). Om een hoge vaccinatiegraad te garanderen wordt deze vaccinatie bij voorkeur georganiseerd binnen de schoolgezondheidszorg (11-13 jaar), maar iedere arts-vaccinator kan deze vaccinatie uitvoeren. Vanaf de leeftijd van 15 jaar wordt voor een inhaalvaccinatie, een drie-dosis regime aanbevolen (HGR 9181).");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        vaccin = new Vaccin("Influenza tetra");
        // vaccin.addJaarInterval(">= 65 jaar");
        vaccin.addMaandInterval("780");
        // vaccin.addJaarInterval("Tijdens zwangerschap"); // ???
        vaccin.addZiekte("Influenza");
        vaccin.setInfo("Een jaarlijkse dosis van het geïnactiveerd vaccin tegen influenza, met 2 stammen van het influenza A-virus en 2 stammen van het influenza B-virus, wordt aanbevolen voor alle volwassenen vanaf de leeftijd van 65 jaar, personen in instellingen, zwangere vrouwen ongeacht de fase van de zwangerschap.\n" +
                "De aanbevelingen over 'seizoensgriep' worden jaarlijks herzien door de HGR (HGR 9488)\n");
        vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

        // ----------------------- READ METHODS -----------------------
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = "";
                data = dataSnapshot.getValue().toString();

                // ----------------------- GET ALL Vaccins  -----------------------
                DataSnapshot vaccins = dataSnapshot.child("vaccin");

                for (DataSnapshot snapshot : vaccins.getChildren()) {
                    Vaccin vaccin = snapshot.getValue(Vaccin.class);
                    Log.d(TAG + " snapshot: ", vaccin.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //click button
        btnSaveVaccin.setOnClickListener(view -> {
            String vaccinName = txtVaccinName.getText().toString();
            String ziekteNaam = txtZiekteNaam.getText().toString();

            if (vaccinName.isEmpty()) {
                Toast.makeText(AddVaccinActivity.this, "Gelieve eerst een vaccin naam in te vullen", Toast.LENGTH_SHORT).show();
            }
            if (ziekteNaam.isEmpty()) {
                Toast.makeText(AddVaccinActivity.this, "Gelieve eerst een ziekte in te vullen", Toast.LENGTH_SHORT).show();
            } else {
                vaccin = new Vaccin(vaccinName);
                vaccin.addZiekte(ziekteNaam);
                vaccinRef.child(vaccin.getNaam()).setValue(vaccin);

                clear();

                Toast.makeText(AddVaccinActivity.this, vaccinName + " opgeslagen!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        //click button
        btnShowListVaccins.setOnClickListener(view -> startActivity(i));
    }

    private void clear() {
        txtVaccinName.setText("");
        txtZiekteNaam.setText("");
    }
}
