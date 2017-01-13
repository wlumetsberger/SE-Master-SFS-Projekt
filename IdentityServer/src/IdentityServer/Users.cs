using IdentityModel;
using IdentityServer4.Test;
using System.Collections.Generic;
using System.Security.Claims;

internal class Users
{
    public static List<TestUser> Get()
    {
        return new List<TestUser> {
            new TestUser {
                SubjectId = "5BE86359-073C-434B-AD2D-A3932222DABE",
                Username = "test",
                Password = "test",
                Claims = new List<Claim> {
                    new Claim(JwtClaimTypes.Name, "test user"),
                    new Claim(JwtClaimTypes.Email, "test@sfs.com"),
                    new Claim(JwtClaimTypes.Role, "user"),
                }
            },
            new TestUser {
                SubjectId = "5BE86359-073C-434B-AD2D-A3932222DABF",
                ProviderSubjectId="adminid",
                Username = "admin",
                Password = "admin",
                Claims = new List<Claim> {
                    new Claim(JwtClaimTypes.Name, "admin user"),
                    new Claim(JwtClaimTypes.Email, "admin@sfs.com"),
                    new Claim(JwtClaimTypes.Role, "admin"),
                }
            }
        };
    }
}