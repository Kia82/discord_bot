import commands.CommandManager;
import listeners.MonkeyListens;



import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


public class DiscordBot {

    private final ShardManager shardManager;

    public DiscordBot() throws InvalidTokenException {

        String token = "";

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT,  GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);


//CACHE: A store of information that discord server can
        //easily access w/o starting a query?? -- longer set of processes

        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        // we don't wanna do ALL, it's typically not a good idea
        //Sometimes it doesn't cache all users so we use
        //chunking filter to make sure it does?
        builder.setChunkingFilter(ChunkingFilter.ALL);
        // FORCES TO CACHE ALL USERS
        builder.enableCache(CacheFlag.ONLINE_STATUS);

        builder.setActivity(Activity.watching("this server"));

        shardManager = builder.build();


        //Register Listeners
        shardManager.addEventListener(new MonkeyListens(), new CommandManager());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args)  {

        try {
            DiscordBot bot = new DiscordBot();
        } catch(InvalidTokenException e) {
            System.out.println("Error: Provided bot token is invalid");
        }
    }



}
