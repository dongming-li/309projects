using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Net;
using System.Web;
using System.Web.Mvc;
using TribesOfCydonia.Models;

namespace TribesOfCydonia.Controllers
{
    public class SandboxViewController : Controller
    {
        private SandboxDBContext db = new SandboxDBContext();

        // GET: SandboxView
        public async Task<ActionResult> Index()
        {
            return View(await db.Sandbox.ToListAsync());
        }

        // GET: SandboxView/Details/5
        public async Task<ActionResult> Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Sandbox sandbox = await db.Sandbox.FindAsync(id);
            if (sandbox == null)
            {
                return HttpNotFound();
            }
            return View(sandbox);
        }

        // GET: SandboxView/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: SandboxView/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create([Bind(Include = "ID,Title")] Sandbox sandbox)
        {
            if (ModelState.IsValid)
            {
                db.Sandbox.Add(sandbox);
                await db.SaveChangesAsync();
                return RedirectToAction("Index");
            }

            return View(sandbox);
        }

        // GET: SandboxView/Edit/5
        public async Task<ActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Sandbox sandbox = await db.Sandbox.FindAsync(id);
            if (sandbox == null)
            {
                return HttpNotFound();
            }
            return View(sandbox);
        }

        // POST: SandboxView/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit([Bind(Include = "ID,Title")] Sandbox sandbox)
        {
            if (ModelState.IsValid)
            {
                db.Entry(sandbox).State = EntityState.Modified;
                await db.SaveChangesAsync();
                return RedirectToAction("Index");
            }
            return View(sandbox);
        }

        // GET: SandboxView/Delete/5
        public async Task<ActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Sandbox sandbox = await db.Sandbox.FindAsync(id);
            if (sandbox == null)
            {
                return HttpNotFound();
            }
            return View(sandbox);
        }

        // POST: SandboxView/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> DeleteConfirmed(int id)
        {
            Sandbox sandbox = await db.Sandbox.FindAsync(id);
            db.Sandbox.Remove(sandbox);
            await db.SaveChangesAsync();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
