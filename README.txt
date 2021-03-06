

Before deploying requirements
=============================

1. Environment variables

Before deploying the service, make sure you have the following environment variables:

export ANT_HOME=/usr/local/ant-1.7.0
export GLOBUS_LOCATION=globus/globus-grid13/ws-core-4.0.3.
export JBOSS_HOME=/usr/local/jboss404

If your GLOBUS_LOCATION does not point to the untarred location of globus.tar, then 
you need to manually replace @@LOGICAL_HOST@@ by ${cadsr.hostname} 
in wsrf.war\WEB-INF\etc\globus_wsrf_core\server-config.wsdd file.

If your JBOSS_HOME does not point to the current JBoss server location, then you need 
to manually copy generated files over.

2. build.properties
You need to modify the values in build.properties file for your specific deployment.

3. JBOSS server status
You need to shutdown JBOSS server.

cd /usr/local/jboss404/server/ncicb-59480/bin
./stop_jboss


Deploying the service
=====================

Deploy help:
ant

Deploy all:
ant deploy

Undeploy:
ant undeploy

Redeploy:
ant redeploy


After deploying the service
===========================

Start JBOSS
cd /usr/local/jboss404/server/ncicb-59480/bin
./start_jboss


Run the test:
ant -f queryRunnerExec.xml -Dservice.url=http://cbvapp-d1008:59480/wsrf/services/cagrid/CaDSRDataService -Dcql.file=exampleCqlQuery.xml



Check JBoss log:
tail -f /usr/local/jboss-4.0.4.GA/server/ncicb-59480/log/server.log


Check Portal:
 dev: http://cbiovdev5017.nci.nih.gov:8080
note: It took 1 hour for the portal to show my service.


