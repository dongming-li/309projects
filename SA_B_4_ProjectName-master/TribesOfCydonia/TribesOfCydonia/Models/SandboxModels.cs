using System;
using System.Data.Entity;

namespace TribesOfCydonia.Models
{
    public class Sandbox
    {
        public int ID { get; set; }
        public string Title { get; set; }
    }

    public class SandboxDBContext : DbContext
    {
        public DbSet<Sandbox> Sandbox { get; set; }
    }
}