using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(TribesOfCydonia.Startup))]
namespace TribesOfCydonia
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            app.MapSignalR();
            ConfigureAuth(app);
        }
    }
}
