package engageglobally.weeklytipapp2;

import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.animation.AlphaAnimation;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    //The textbox where the tips will be shown
    public static TextView mTextView;

    //Manages the alarm tips
    AlarmManager alarmManager;

    //Manages the date for reset
    public Calendar calendar;

    //The array of quotes
    public static ArrayList<String> quotes;


    @Override
    /**
     * Name: onStart()
     * Purpose: Calls onStart of superclass
     */
    public void onStart(){
        super.onStart();
    }

    /**
     * Name: scheduleAlarm()
     * Purpose: Schedules the alarm for when the tip should be reset
     */
    private void scheduleAlarm()
    {
        //Gets the current date/time information
        Calendar calendar = Calendar.getInstance();

        //Sets the reset time at Sunday midnight
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        //Ensures that the alarm is set for the coming Sunday
        if(calendar.getTimeInMillis() < System.currentTimeMillis())
        {
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }

        //Creates a new Intent to transfer to the AlarmReceiver
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

        //Creates a PendingIntent to switch at the reset time
        PendingIntent myIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        //Instantiates and sets the alarm
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, myIntent);
    }

    @Override
    /**
     * Name: onCreate(Bundle savedInstanceState)
     * Purpose: Schedules the alarm and populates the database with tips
     * @param Bundle savedInstanceState: To restore any previously saved settings of the app
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        //Calls superclass method
        super.onCreate(savedInstanceState);

        //Sets the XML layout of the app
        setContentView(R.layout.activity_main);

        //Gets the textbox from the layout to edit
        mTextView = (TextView) findViewById(R.id.quote_block);

        //Schedules the alarm
        scheduleAlarm();

        //Creates a new quotes database
        quotes = new ArrayList<String>();

        //Week 1
        quotes.add("<p>\"Effective reduction of environmental impact typically begins with measurement to understand where you are starting from. This <a href='http://footprint.wwf.org.uk/' WWF tool </a> allows individuals to assess their environmental footprint on key variables. Once you complete the survey, think about which areas you can most easily address.\"</p>");

        //Week 2
        quotes.add("<p>\"<b>Pick up trash:</b> At Engage we encourage picking up trash whenever and wherever you can. Not only do you help keep your community and waterways clean, but you also role model caring for our local environment.\"</p>");

        //Week 3
        quotes.add("<p>\"Scientists estimate by 2030 that there will be a larger volume of plastic in the oceans than volume of fish. We can help by picking up plastic wherever we see it; using less plastic; recycling plastic; and organizing local clean-ups. Want to magnify your voice? Encourage stores and consumers to always use reusable bags. Ask businesses to increase the amount of recycled plastic in their packaging. Or ask your local city or state to consider putting a tax on plastic or banning the use of plastic bags and bottles. Learn more about the plastic problem <a href='https://www.washingtonpost.com/news/energy-environment/wp/2017/07/19/theres-literally-a-ton-of-plastic-garbage-for-every-person-in-the-world/?utm_term=.51e46b460ab6' here </a>\"</p>");

        //Week 4
        quotes.add("<p>\"Would you like to volunteer locally to help protect the environment? There are many possibilities in most communities. You can consider participating in a park clean-up or organizing one yourself. Contact local parks, county parks, state parks, and national protected areas to see when clean-ups are being held. Or, participate in the international oceans clean-up <a href='https://oceanconservancy.org/trash-free-seas/international-coastal-cleanup/' here </a>\"</p>");

        //Week 5
        quotes.add("<p>\"Want to volunteer to help protect the environment? Consider volunteering in a community garden or helping to create one. Some gardens produce food for local use and others are designed to help pollinators. This is a great way to clean-up abandoned properties and to bring communities together to create healthy and green spaces. Find a community garden <a href='https://communitygarden.org/find-a-garden/' near you</a>\"</p>");

        //Week 6
        quotes.add("<p>\"Americans spend about $20 billion on Valentine’s Day. Many of the products purchased are imported from far away and have significant environmental and social impacts. Alternatives include: purchasing local art for Valentine’s Day; cooking a meal with locally grown foods; donating in your loved one’s name to their favorite charity; planting live flowering plants together to love the pollinators; and visiting your favorite park together. To magnify your voice, ask your local schools and members of community groups to shift from chocolate and internationally imported flowers to local alternatives.\"</p>");

        //Week 7
        quotes.add("<p>\"Getting ready to plant for the spring?  Want to help save the Monarch Butterfly? Plant native milkweed in your home, office, and community. Monarch populations are estimated to have declined over 90% in the last 20 years.  Learn more <a href='http://blog.nwf.org/2015/02/twelve-native-milkweeds-for-monarchs/' here.</a> Have a photo of milkweed, monarch caterpillars, or monarchs that you would like to share? Post it on our <a href='https://www.facebook.com/EngageGlobally/' Facebook page.</a>\"</p> ");

        //Week 8
        quotes.add("<p>\"Bees and other pollinating insects, along with bats, have severely <a href='https://www.washingtonpost.com/news/energy-environment/wp/2016/05/13/more-bad-news-for-honeybees-beekeepers-lost-nearly-half-their-colonies-in-the-past-year/?utm_term=.22bfd70a3871' declined in populations. </a>You can help by planting pollinator plants. Magnify your voice by asking local nurseries and plant sellers to have a pollinator plant display and guides for pollinator gardening. Learn more <a href='https://www.fs.fed.us/wildflowers/pollinators/gardening.shtml' here.</a>\"</p>");

        //Week 9
        quotes.add("<p>\"Help save the bees! Plant bee friendly plants and ask your local community to create bee friendly gardens and to plant bee friendly plants on shared roadways. Live in an apartment? Ask your apartment manager if they will allow residents to create a small community garden or if they will landscape with pollinator plants. Reduce the use of pesticides to help all pollinating insects. Learn more <a href='http://thehoneybeeconservancy.org/act-today/plant-a-bee-garden/' here.</a>\"</p>");

        //Week 10
        quotes.add("<p>\"<b>Use alternative energy:</b> Contact your electricity company and natural gas or heating companies to ask about their renewable energy option. With many companies, consumers can now purchase wind or solar power. By making this purchase, consumers are helping to increase demand for renewables and driving rapid growth in this industry. Want to magnify your voice? Consider installing solar panels or ask your employer, local community groups, faith organizations, or local businesses to buy renewable power.\"</p>");

        //Week 11
        quotes.add("<p>\"Transportation is often one of an individual’s largest sources of carbon emissions. You can reduce your transportation footprint by: taking public transit; walking or biking when possible; buying fuel efficient, hybrid, or electric cars; and carbon offsetting your transportation use.\"</p>");

        //Week 12
        quotes.add("<p>\"Studies suggest that over 40% of food purchased in the United States is thrown away uneaten. This has significant environmental impacts including: pesticide; fertilizer; and water use to grow the food; transportation costs to market; and methane emissions from decomposition. We can help address this problem by: being willing to purchase ‘ugly’ fruits and vegetables; asking stores to have ‘ugly’ fruit and vegetable displays; reducing what we purchase in order to throw away less; purchasing locally; and asking local communities to create composting programs. Learn more <a href='https://www.epa.gov/recycle/reducing-wasted-food-home' here.</a>\"</p>");

        //Week 13
        quotes.add("<p>\"<b>Are you registered to vote?</b> Learn how <a href='https://www.vote.org/register-to-vote/' here</a>\"</p> ");

        //Week 14
        quotes.add("<p>\"Want to learn more about environmental issues? We like to read BBC’s science and environment coverage <a href='http://www.bbc.com/news/science_and_environment' here </a>. But, our favorite recent videos are from <a href='http://www.bbcearth.com/planetearth2/' Planet Earth 2</a>.\"</p>");

        //Week 15
        quotes.add("<p>\"The US Bureau of Labor estimates that the average American household spends $1700 on clothing each year. The environmental and social impact of what is being called, ‘Fast Fashion’, are significant. To help: buy less clothes; wear clothes longer; donate clothes to local used clothing shops; buy used clothing; upcycle clothing into art or household products; and when buying clothes buy ones made locally and with sustainable products – especially cotton.\"</p>");

        //Week 16
        quotes.add("<p>\"This year, our Director received a wonderful gift – a pair of sneakers made from recycled ocean plastic produced by Adidas’ partnership with Parley for the Oceans. Many new products are being created out of recycled plastic taken out of the oceans. These products will last beyond our lifetimes and help increase demand for cleaning our oceans. Learn more about the shoes <a href='http://www.adidas.com/us/parley' here</a>.\"</p>");

        //Week 17
        quotes.add("<p>\"Did a company do something sustainable that you appreciated? Send them an email thanking them for their efforts. Positive feedback can lead decision-makers to make further progress. This is especially true with small and local businesses.\"</p>");

        //Week 18
        quotes.add("<p>\"Use compostable <a href='http://www.fabri-kal.com/product-solutions/greenware/' Greenware</a> at your business, for parties, and events. Magnify your voice by encouraging restaurants and other places where you shop to use Greenware instead of single use non-compostable products.\"</p>");

        //Week 19
        quotes.add("<p>\"If your cats go outdoors, <a href='http://www.audubon.org/news/how-stop-cats-killing-birds' consider</a> using a bell or a Birdbesafe collar.\"</p>  ");

        //Week 20
        quotes.add("<p>\"Reducing meat consumption can be good for your health and the environment, as it reduces deforestation, water use, agricultural waste, and methane. For #Sustainable Saturday, go meat free! Try grilled vegetables and a salad or our favorite, vegetarian ‘chicken’ nuggets!\"</p> ");

        //Week 21
        quotes.add("<p>\"Planning to travel this summer? You can reduce your impact by using hotels that have local or global environmental certifications, offsetting your air and other transportation carbon emissions, eat local foods whenever possible, carry a reusable water bottle and reusable shopping bags, be very careful when buying souvenirs that they are not made from endangered animal products, and never get close to a wild animal (feeding it or taking selfies with it). More tips <a href='https://www.lonelyplanet.com/travel-tips-and-articles/how-to-go-green-when-you-travel/40625c8c-8a11-5710-a052-1479d27679e5' here.</a>\"</p>");

        //Week 22
        quotes.add("<p>\"Overfishing and destructive fishing are significantly impacting fish populations and ocean health. You can help by using the Monterey Seafood Watch guide if you purchase seafood.  You can magnify your voice by encouraging grocery stores and restaurants to label their fish and to sell only sustainably sourced fish as certified by the <a href='http://www.seafoodwatch.org/seafood-recommendations/our-app' Marine Stewardship Council</a>.\"</p> ");

        //Week 23
        quotes.add("<p>\"<b>Walk or bike whenever possible.</b> Take the bus or carpool if one of these is not practical. Walking or cycling reduces the amount of gas spewed into the air by cars thus lowering carbon emissions greatly. In addition to having less of an impact on our planet you can become a little more fit and healthy, so make it part of your exercise program. To see more reasons why walking or biking is better, click <a href='https://www.livecareer.com/quintessential/bike-walk-work' here</a>. National Bike-to-Work day is the 3rd Friday of May every year.\"</p> ");

        //Week 24
        quotes.add("<p>\"<b>Borrow Instead of Buying.</b> Rent movies, borrow books from libraries and buy secondhand goods when at all possible. By purchasing and using pre-owned items, you reduce those items that need to be kept in a landfill and save yourself money. Living green doesn’t mean you have to settle for less, many items that you find in a used-goods store are just as good as the original. Search the web or explore your town for some neat items. To find out what your library does and to learn more about America’s libraries, click <a href='http://www.ilovelibraries.org/what-libraries-do' here</a>. <a href='https://www.peerby.com' Peerby</a> is a website that enables you to borrow the things you need from people in your neighborhood.\"</p>");

        //Week 25
        quotes.add("<p>\"<b>Xeriscape</b> with drought-tolerant native species in your yard. Xeriscaping can reduce landscape water use by 50-75%, along with less maintenance and other benefits. To find out more reasons to switch, click <a href='http://eartheasy.com/grow_xeriscape.htm' here</a>. Plant shade trees and vines to keep you and your home cool in the summer. Click <a href='http://www.ose.state.nm.us/WUC/Albq-brochures/Xeric-Guide.pdf' here</a> to view an easy how-to guide with tips and eight simple steps.\"</p> ");

        //Week 26
        quotes.add("<p>\"Switch to biodegradable cleaning products to help reduce chemicals in our water and soil. Try to avoid using microbeads in any purchase. Learn more <a href='http://storyofstuff.org/plastic-microbeads-ban-the-bead/' here</a>.\"</p>");

        //Week 27
        quotes.add("<p>\"Traveling this summer? Consider carbon offsetting your travels. Many airlines now allow for carbon offsetting of flights during ticket purchase. You can also carbon offset to protect forests or for projects like clean stove cooking. Traveling by air across the US uses approximately 2 tons of Carbon Dioxide per person. You can carbon offset for $10 per ton. Donate to <a href='www.engageglobally.org' Engage Globally </a> and we will use your carbon offset funding towards purchase of land adjacent to the Children’s Eternal Rainforest and reforesting with native plants and trees.\"</p>");

        //Week 28
        quotes.add("<p>\"Support local farmers’ markets when possible. Buying local produce generally reduces environmental impact of transportation, often encourages organic production, and reduces food waste. Magnify your impact by ensuring that your local farmers market is accessible to everyone in your community. Learn more <a href='https://sustainableconnections.org/why-buy-local/' here</a>.\"</p>");

        //Week 29
        quotes.add("<p>\"<b>Install low-flush toilets</b> and drop from six gallons per flush to one and a half. It saves money as well as water, by cutting utility bills and/or septic tank service calls. For a list of the best low-flush toilets reviewed in 2017, click <a href='http://homeworthylist.com/best-low-flow-toilet-reviews/' here</a>.\"</p>");

        //Week 30
        quotes.add("<p>\"<b>Reduce Junk Mail.</b> Researchers at San Jose State University estimate U.S. adults receive an average of 41 pounds of junk mail per year. Ecocycle provides resources and links where you can sign up to be removed from mailing lists and reduce junk mail: <a href='http://www.ecocycle.org/junkmail'> here </a>\"</p>");

        //Week 31
        quotes.add("<p>\"<b>Reduce junk mail from charities:</b> To reduce our environmental impact, Engage Globally uses digital communications. Charity Navigator offers tips for reducing unwanted charity solicitations: <a href='https://www.charitynavigator.org/index.cfm?bay=content.view&cpid=254' here </a>\"</p>");

        //Week 32
        quotes.add("<p>\"<b>Receive and Pay Your Bills Online:</b> Most bills can now be viewed and paid online. Contact your service providers and set up online billing and bill pay today! Learn more <a href='http://www.ebillplace.com/cda/ebillplace/gogreen/go-green.html' here </a>\"</p>");

        //Week 33
        quotes.add("<p>\"<b>Turn Off Your Electronics When Not In Use:</b> A computer left on for 24 hours can use a lot of electricity – up to 1000 kilowatts. Remember to turn off your devices, to use power strips, and to help others reduce energy use by placing reminder signs on shared light fixtures and other devices. \"</p>");

        //Week 34
        quotes.add("<p>\"<b>Avoid Phantom Power,</b> which can be up to 10% of an average consumer’s electricity bill. Many devices use electricity even they are “off” because they use power for clocks, LED lights, remote signals, or the power strip they are plugged into. To reduce phantom power, use smart power strips. They can save you money and reduce your energy use! <a href='http://science.howstuffworks.com/environmental/green-tech/sustainable/smart-power-strip.htm' here </a> \"</p>");

        //Week 35
        quotes.add("<p>\"<b>Use alternatives to Styrofoam,</b> which is rarely recyclable and can take centuries to degrade. Use a travel mug for buying hot beverages. And, in your work places and community organizations purchase recyclable paper products, especially ones made from post-consumer waste. You can magnify your voice by asking frequently used businesses to use alternatives to Styrofoam.\"</p>");

        //Week 36
        quotes.add("<p>\"<b>We love our reusable shopping bags at Engage Globally! </b>, Did you know that over 150,00 plastic bags are used globally every second?  Plastic pollution is a significant threat to our waterways and oceans. Here at Engage, we like to use bags made from recycled plastic. You can magnify your impact by using recycled plastic shopping bags as gift bags or ordering them to promote your business, community group, or cause from businesses like:  <a href'https://www.customearthpromos.com/eco-friendly-reusable-bags/recycled-bags.html ' here </a>\"</p>");

        //Week 37
        quotes.add("<p>\"<b>Skip the straw! </b>Americans use an estimated 500 million straws per day (1.6 straws per person). Growing recognition of the damage straws cause as pollutants in public spaces, beaches, and oceans and their impact on wildlife has led to some restaurants and many consumers switching to reusable or compostable straws and reducing their use. When eating out you can decline the straw and ask restaurants and businesses to use compostable products. You can also purchase reusable straws from many retailers.\"</p>");

        //Week 38
        quotes.add("<p>\"<b>Take Shorter Showers. </b>The average American uses an estimated 25,000 gallons of water a year. Use of hot water in showers also consumes significant amounts of energy. Taking shorter showers can both save water and reduce energy use.  An easy way to reduce shower time is to time yourself to see how long your shower normally lasts and then use a timer to reduce your shower time by 10%. Cutting just one minute will save about 5 gallons of water. \"</p>");

        //Week 39
        quotes.add("<p>\"<b>Bring your own mug! </b>The average office worker in the U.S. throws away 1.4 cups per day. Help reduce this waste by bringing your own mug. You can make a broader difference by asking your work place to give everyone a mug, which helps build community while saving money and reducing environmental impact.\"</p>");

        //Week 40
        quotes.add("<p>\"<b>Bring your own bottle! </b>In the U.S. we throw away about 2.5 million plastic bottles each hour. Only about 9% of these bottles are recycled and many end up discarded in public spaces or in our waterways and oceans. You can reduce use of plastic bottles by bringing your own bottle and can make a broader difference by encouraging your workplace, community groups, and businesses to not purchase plastic bottled drinks.\"</p>");

        //Week 41
        quotes.add("<p>\"<b>Upgrade your lights. </b>Compact fluorescent bulbs use less energy and last longer. Motion sensitive lights can save a lot of money. And, outdoor solar powered lights are increasingly popular and affordable. To learn more about innovations in lighting see: <a href'http://environment.nationalgeographic.com/environment/green-guide/buying-guides/lightbulb/shopping-tips/' here </a>\"</p>");

        //Week 42
        quotes.add("<p>\"<b>Air Dry Your Clothes. </b>Hang your wet clothes on a drying line or rack instead of using a powered dryer. This will reduce energy use and save you money. \"</p>\n");

        //Week 43
        quotes.add("<p>\"Forbes estimates that Americans spend more than 8 billion dollars annually on Halloween. To reduce Halloween’s environmental impact, consider: reusing costumes; making costumes out of recycled products; using non-food gifts; and buying rainforest certified chocolate. If you save money, consider making a donation to your favorite charity. More tips <a href='https://www.worldwildlife.org/pages/10-green-halloween-tips' here </a>\"</p> ");

        //Week 44
        quotes.add("<p>\"<b>Are you ready to vote?</b> We can learn about local elections through local media. To learn about the environmental positions of national level candidates, see <a href='http://www.ontheissues.org/Environment.htm' here </a>\"</p> ");

        //Week 45
        quotes.add("<p>\"Don't forget to vote!\"</p>");

        //Week 46
        quotes.add("<p>\"Giving Tuesday is Nov. 28th, 2017. To choose a charity to contribute to we encourage you to carefully consider their website and work. For well-known charities, you can see an independent evaluation at <a href='www.charitywatch.org' charitywatch.org</a>. For smaller or newer organizations, evaluations can be found at <a href='www.charitynavigator.org' charitynavigator.org</a>\"</p>");

        //Week 47
        quotes.add("<p>\"Celebrating Thanksgiving with friends and family? You can green your Thanksgiving by avoiding disposable plates, buying locally and organically when possible, serving tap water, sending leftovers home with guests. Want to give your guests a plant? Consider giving a live plant or together planting an evergreen tree each year.\"</p>  ");

        //Week 48
        quotes.add("<p>\"Please consider contributing to Engage Globally for Giving Tuesday, 11/28/17, at <a href='www.engageglobally.org' our website</a>. Your donation will support community-led sustainable development in education and conservation.\"</p>");

        //Week 49
        quotes.add("<p>\"This holiday season, if you’re giving gifts, consider giving charitable donations. For children, try stuffed endangered species from the Smithsonian Zoo or World Wildlife Fund. For adults, you can make a donation in someone’s name and receive a gift card or card insert. With <a href='www.engageglobally.org' Engage Globally </a>you can donate to save an acre of rainforest or to send a child to school.\"</p>");

        //Week 50
        quotes.add("<p>\"<b>Be thoughtful about animal videos on social media: </b>Please avoid posting videos and photos of wild animals that are kept as pets and those that look like pets in rescue centers. If promoting a rescue center, Google to be sure the center is reputable and indicate clearly in your post that this is a rescue center. The wild animal pet trade is devastating populations of many birds, primates, and small mammals. \"</p>");

        //Week 51
        quotes.add("<p>\"<b>Print thoughtfully: </b>The Clean Air Council estimates the average American uses 650 pounds of paper per year, or 8 trees. We can reduce our paper usage by: printing less and using digital alternatives; printing on recycled paper; and double-siding our printing.\"</p>");

        //Week 52
        quotes.add("<p>\"<b>Wash clothes in cold or warm water: </b>The EPA’s Energy Star program estimated that 90% of the energy used in washing clothes came from heating the water. The Sierra Club estimated that households that switched to cold water washing could eliminate 1600 pounds of CO2 emissions annually. \"</p>");

        //Week 53
        quotes.add("<p>\"There’s still time to make a tax deductible donation to your favorite charity, like <a href= 'www.engageglobally.org' Engage Globally. </a>\"</p>");

        //Displays the current week's quotes
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        mTextView.setText(Html.fromHtml(quotes.get(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)-1)));
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mTextView.startAnimation(fadeIn);
        fadeIn.setDuration(2000);
        fadeIn.setFillAfter(true);
    }

    /**
     * Name: change()
     * Purpose: Updates the displayed quote if the app is open
     */
    public static void change()
    {
        mTextView.setText(Html.fromHtml(quotes.get(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)+1)));
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * Name: getText()
     * Purpose: Returns the currently displayed text
     * @return the displayed text string
     */
    public static String getText()
    {
        return mTextView.getText().toString();
    }
}
