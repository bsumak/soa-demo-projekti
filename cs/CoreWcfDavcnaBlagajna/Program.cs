using CoreWCF;
using CoreWCF.Configuration;
using CoreWCF.Description;
using CoreWcfDavcnaBlagajna.Services;

var builder = WebApplication.CreateBuilder(args);

builder.WebHost.UseUrls("http://0.0.0.0:8082");

// CoreWCF + metadata
builder.Services.AddServiceModelServices();
builder.Services.AddServiceModelMetadata();

// DI
builder.Services.AddSingleton<DavcnaBlagajna>();

var app = builder.Build();

app.UseServiceModel(sb =>
{
    var binding = new BasicHttpBinding();

    // registriraj storitev in endpoint
    sb.AddService<DavcnaBlagajna>();
    sb.AddServiceEndpoint<DavcnaBlagajna, IDavcnaBlagajna>(
        binding, "/DavcnaBlagajna");

    // WSDL prek HTTP GET
    var smb = app.Services.GetRequiredService<ServiceMetadataBehavior>();
    smb.HttpGetEnabled = true;
});

app.Run();
