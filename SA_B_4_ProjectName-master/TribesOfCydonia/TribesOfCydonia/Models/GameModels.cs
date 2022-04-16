using System.Data.Entity;

namespace TribesOfCydonia.Models
{
    public class Game
    {
        public int ID { get; set; }
        public string Title { get; set; }
        public int TurnNumber { get; set; }
        public int[] PlayerOrder { get; set; }
        public Player CurrentPlayer { get; set; }
    }

    public class Player
    {
        public int ID { get; set; }
        public string Name { get; set; }
    }

    public class GameDBContext : DbContext
    {
        public DbSet<TribesOfCydonia.Models.Game> Games { get; set; }
    }

}