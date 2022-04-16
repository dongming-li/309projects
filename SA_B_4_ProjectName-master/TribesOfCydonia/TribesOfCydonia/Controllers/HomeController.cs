using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace TribesOfCydonia.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Play()
        {
            ViewBag.Message = "Play message set in HomeController.cs Play() method.";

            return View();
        }

        public ActionResult Rules()
        {
            ViewBag.Message = "Rules message set in HomeController.cs Rule() method.";

            return View();
        }

        public ActionResult Chat()
        {
            ViewBag.Message = "Message set in HomeController.cs Chat() method.";

            return View();
        }
    }
}